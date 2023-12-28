package com.shreyas.nytimes.di

import com.shreyas.nytimes.MainApplication
import com.shreyas.nytimes.di.modules.GithubRepoModule
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.Before
import org.junit.Test
import java.time.Instant

class GithubRepoModuleTest {

    private val mockApplication = mockk<MainApplication>()
    private lateinit var module: GithubRepoModule

    @Before
    fun setup() {
        module = FakeGithubRepoModule()
    }

    @Test
    fun `test provide context returns expected application context`() {
        every { mockApplication.applicationContext } returns mockApplication

        val result = GithubRepoModule.provideApplicationContext(mockApplication)

        assertThat(result, equalTo(mockApplication))
        verify { mockApplication.applicationContext }

        Instant.now()
    }

    class FakeGithubRepoModule : GithubRepoModule()
}