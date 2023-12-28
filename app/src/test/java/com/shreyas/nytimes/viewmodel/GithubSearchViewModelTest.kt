package com.shreyas.nytimes.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.shreyas.nytimes.model.GitHubSearchResponse
import com.shreyas.nytimes.repository.GitHubSearchRepositoryImpl
import com.shreyas.nytimes.utils.ResultWrapper
import com.shreyas.nytimes.utils.TestJsonUtils.getObjectFromJsonFile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
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

    private lateinit var viewModel: GithubSearchViewModel

    @Mock
    private lateinit var githubRepoSearchObserver: Observer<GitHubSearchResponse?>

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = GithubSearchViewModel(mockRepository)
    }

    @Test
    fun `view model is not null`() {
        assertThat(viewModel).isNotNull()
    }

    @Test
    fun `on http success fetch github search response results are expected`() {
        runTest {
            val response = getObjectFromJsonFile(
                jsonFile = "success_github_result.json",
                tClass = GitHubSearchResponse::class.java
            )

            val liveDataResponse = MutableLiveData<GitHubSearchResponse>()
            liveDataResponse.value = response

            doReturn(ResultWrapper.SUCCESS(liveDataResponse)).`when`(mockRepository)
                .getMostPopularGitHubRepos("square")

            viewModel.gitHubSearchResponse.observeForever(githubRepoSearchObserver)

            viewModel.fetchMostPopularGitHubRepos("square")

            verify(mockRepository, times(1)).getMostPopularGitHubRepos(anyString())
        }
    }

    @Test
    fun `on http error fetch github search response as empty`() {
        runTest {
            val response = getObjectFromJsonFile(
                jsonFile = "success_github_empty_result.json",
                tClass = GitHubSearchResponse::class.java
            )

            doReturn(ResultWrapper.FAILURE(null)).`when`(mockRepository)
                .getMostPopularGitHubRepos("square")

            viewModel.gitHubSearchResponse.observeForever(githubRepoSearchObserver)

            viewModel.fetchMostPopularGitHubRepos("square")
        }
    }

    @After
    fun tearDown() {
        viewModel.gitHubSearchResponse.removeObserver(githubRepoSearchObserver)
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
