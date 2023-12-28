package com.shreyas.nytimes.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.verify
import com.shreyas.nytimes.model.GitHubSearchResponse
import com.shreyas.nytimes.repository.GitHubSearchRepositoryImpl
import com.shreyas.nytimes.utils.TestJsonUtils.getObjectFromJsonFile
import com.shreyas.nytimes.utils.testObserver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers.anyString
import org.mockito.ArgumentMatchers.isNotNull
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class GithubSearchViewModelTest {

    @get:Rule
    val instantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Mock
    private lateinit var mockRepository: GitHubSearchRepositoryImpl

    @Mock
    private lateinit var mockViewModel: GithubSearchViewModel

    @Mock
    private lateinit var githubRepoSearchObserver: Observer<GitHubSearchResponse?>

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        mockViewModel = GithubSearchViewModel(mockRepository)
    }

    @Test
    fun `view model is not null`() {
        assertThat(mockViewModel, isNotNull())
    }

    @Test
    fun `github response live data has success data`() {
        val githubResponse = getObjectFromJsonFile(
            jsonFile = "success_github_result.json",
            tClass = GitHubSearchResponse::class.java
        )
        mockViewModel._gitHubSearchResponse.value = githubResponse
        val githubRepoResponseLiveData = mockViewModel.gitHubSearchResponse.testObserver()

        assertThat(githubRepoResponseLiveData.observedValues, equalTo(githubResponse))
    }

    @Test
    fun `github response live data has error`() {
        mockViewModel._gitHubSearchResponse.value = null
        val errorGithubResponseLiveData = mockViewModel.gitHubSearchResponse.testObserver()
        assertThat(errorGithubResponseLiveData.observedValues, equalTo(null))
    }

    @Test
    fun `on http success fetch github search response results are expected`() {
        runTest {
            val response = getObjectFromJsonFile(
                jsonFile = "success_github_result.json",
                tClass = GitHubSearchResponse::class.java
            )

            mockViewModel._gitHubSearchResponse.value = response
            mockViewModel.gitHubSearchResponse.observeForever(githubRepoSearchObserver)

            doReturn(mockViewModel.gitHubSearchResponse).`when`(mockRepository)
                .getMostPopularGitHubRepos(anyString())

            mockViewModel.fetchMostPopularGitHubRepos("square")

            assertThat(mockViewModel.gitHubSearchResponse.value, equalTo(response))

            verify(mockRepository).getMostPopularGitHubRepos(anyString())
        }
    }

    @Test
    fun `on http error fetch github search response as empty`() {
        runTest {
            val response = getObjectFromJsonFile(
                jsonFile = "success_github_empty_result.json",
                tClass = GitHubSearchResponse::class.java
            )

            mockViewModel._gitHubSearchResponse.value = response
            mockViewModel.gitHubSearchResponse.observeForever(githubRepoSearchObserver)

            doReturn(mockViewModel.gitHubSearchResponse).`when`(mockRepository)
                .getMostPopularGitHubRepos(anyString())

            mockViewModel.fetchMostPopularGitHubRepos("square")

            assertThat(mockViewModel.gitHubSearchResponse.value, equalTo(""))

            verify(mockRepository).getMostPopularGitHubRepos(anyString())
        }
    }

    @After
    fun tearDown() {
        mockViewModel.gitHubSearchResponse.removeObserver(githubRepoSearchObserver)
    }

    // Reusable JUnit4 TestRule to override the Main dispatcher
    @OptIn(ExperimentalCoroutinesApi::class)
    class MainDispatcherRule(
        private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()
    ) : TestWatcher() {
        override fun starting(description: Description) {
            Dispatchers.setMain(testDispatcher)
        }

        override fun finished(description: Description) {
            Dispatchers.resetMain()
        }
    }
}
