package com.shreyas.nytimes.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
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
                TopAppBarWithSearch()
                GitHubRepoRecyclerviewComposable()
            }
        }
    }

    @Composable
    fun TopAppBarWithSearch() {
        TopAppBar(
            elevation = 10.dp,
        ) {

        }
    }

    @Composable
    internal fun GitHubRepoRecyclerviewComposable() {
        LazyColumn {

        }
    }
}