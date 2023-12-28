package com.shreyas.nytimes.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.shreyas.nytimes.base.MockServerBaseTest
import com.shreyas.nytimes.service.GitHubRepoService
import com.shreyas.nytimes.utils.ResultWrapper
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.net.HttpURLConnection

@RunWith(JUnit4::class)
class GitHubSearchRepositoryImplTest : MockServerBaseTest() {

    @get:Rule
    val instantTaskExecutorRule: TestRule = InstantTaskExecutorRule()
    override fun isMockServerEnabled(): Boolean = true

    private lateinit var repositoryImpl: GitHubSearchRepositoryImpl
    private lateinit var service: GitHubRepoService

    @Before
    fun setUp() {
        service = provideGithubRepoService()
        repositoryImpl = GitHubSearchRepositoryImpl(service)
    }

    @Test
    fun `given response is 200 when fetching github response and returns success`() =
        runTest {
            mockHttpResponseFromFile("success_github_result.json", HttpURLConnection.HTTP_OK)
            when (val result = repositoryImpl.getMostPopularGitHubRepos("square")) {
                is ResultWrapper.SUCCESS -> {
                    val githubResult = result.value.value
                    assertThat(githubResult).isNotNull()
                    assertThat(githubResult?.total_count).isEqualTo(2)
                    assertThat(githubResult?.items?.size).isEqualTo(2)
                }

                else -> {}
            }
        }

    @Test
    fun `given response is 200 when fetching github response and returns empty list response`() =
        runTest {
            mockHttpResponseFromFile("success_github_empty_result.json", HttpURLConnection.HTTP_OK)
            when (val result = repositoryImpl.getMostPopularGitHubRepos("square")) {
                is ResultWrapper.SUCCESS -> {
                    val githubResult = result.value.value
                    assertThat(githubResult).isNotNull()
                    assertThat(githubResult?.total_count).isEqualTo(0)
                    assertThat(githubResult?.items?.size).isEqualTo(0)
                }

                else -> {}
            }
        }

    @Test
    fun `given response is 403 when fetching github response and returns exception`() =
        runTest {
            mockHttpResponse(403)
            when (val result = repositoryImpl.getMostPopularGitHubRepos("square")) {
                is ResultWrapper.FAILURE -> {
                    assertThat(result).isNotNull()
                    val expectedResponse = ResultWrapper.FAILURE(null)
                    assertThat(expectedResponse.code).isEqualTo(result.code)
                }

                else -> {}
            }
        }
}