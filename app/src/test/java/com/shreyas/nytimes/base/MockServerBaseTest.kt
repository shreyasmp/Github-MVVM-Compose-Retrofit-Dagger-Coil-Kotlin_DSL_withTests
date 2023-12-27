package com.shreyas.nytimes.base

import com.shreyas.nytimes.service.GitHubRepoService
import com.shreyas.nytimes.utils.TestJsonUtils.getResponseAsString
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

abstract class MockServerBaseTest {

    private lateinit var mockServer: MockWebServer

    @Before
    open fun setup() {
        this.startMockServer()
    }

    abstract fun isMockServerEnabled(): Boolean

    open fun startMockServer() {
        if (isMockServerEnabled()) {
            mockServer = MockWebServer()
            mockServer.start()
        }
    }

    open fun stopMockServer() {
        if (isMockServerEnabled()) {
            mockServer.shutdown()
        }
    }

    @After
    open fun tearDown() {
        this.stopMockServer()
    }

    open fun mockHttpResponseFromFile(fileName: String, responseCode: Int) =
        mockServer.enqueue(
            MockResponse().setResponseCode(responseCode).setBody(getResponseAsString(fileName))
        )

    open fun mockHttpResponse(responseCode: Int) =
        mockServer.enqueue(MockResponse().setResponseCode(responseCode))


    // Creating a test github repo service
    fun provideGithubRepoService(): GitHubRepoService {
        return Retrofit.Builder().baseUrl(mockServer.url("/")).addConverterFactory(
            GsonConverterFactory.create()
        ).client(OkHttpClient.Builder().build()).build().create(GitHubRepoService::class.java)
    }
}