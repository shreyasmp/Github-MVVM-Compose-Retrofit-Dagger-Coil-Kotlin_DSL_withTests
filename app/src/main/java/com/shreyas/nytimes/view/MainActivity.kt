package com.shreyas.nytimes.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.shreyas.nytimes.R
import com.shreyas.nytimes.ui.MainScreen
import com.shreyas.nytimes.view.theme.GitHubRepoComposeAppTheme
import com.shreyas.nytimes.viewmodel.GithubSearchViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }

    lateinit var viewModel: GithubSearchViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this, viewModelFactory)[GithubSearchViewModel::class.java]

        setContent {
            GitHubRepoComposeAppTheme {
                TopAppBarWithTitle()
                MainScreen()
            }
        }
    }

    @Preview
    @Composable
    fun TopAppBarWithTitle() {
        TopAppBar(
            title = {
                Text(text = this.getString(R.string.app_bar_title))
            }
        )
    }

    @Composable
    internal fun GitHubRepoRecyclerviewComposable() {
        LazyColumn {

        }
    }
}