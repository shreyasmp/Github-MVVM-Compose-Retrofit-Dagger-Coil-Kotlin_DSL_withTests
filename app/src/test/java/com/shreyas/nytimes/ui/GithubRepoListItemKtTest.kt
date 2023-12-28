package com.shreyas.nytimes.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.shreyas.nytimes.model.OwnerData
import com.shreyas.nytimes.model.RepositoryData
import com.shreyas.nytimes.ui.theme.GithubRepositoryTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class GithubRepoListItemKtTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var mockRepoData: RepositoryData

    @Before
    fun setup() {
        mockRepoData = RepositoryData(
            name = "okhttp",
            description = "Square’s meticulous HTTP client for the JVM, Android, and GraalVM.",
            stargazers_count = 44786,
            forks_count = 9275,
            updated_at = "2023-12-12T02:20:23Z",
            html_url = "https://github.com/square/okhttp",
            owner = OwnerData(avatar_url = "https://avatars.githubusercontent.com/u/82592?v=4")
        )
        composeTestRule.setContent {
            GithubRepositoryTheme {
                Surface(color = MaterialTheme.colors.background) {
                    GithubRepoListItem(repoData = mockRepoData)
                }
            }
        }
    }

    @Test
    fun `test github list item composable component`() {
        composeTestRule.run {
            onNodeWithText("okhttp").assertIsDisplayed()
            onNodeWithText("Square’s meticulous HTTP client for the JVM, Android, and GraalVM.")
                .assertIsDisplayed()
            onNodeWithText("44786").assertIsDisplayed()
            onNodeWithText("9275").assertIsDisplayed()
            onNodeWithText("12-Dec-2023 02:20:23").assertIsDisplayed()
        }
    }
}