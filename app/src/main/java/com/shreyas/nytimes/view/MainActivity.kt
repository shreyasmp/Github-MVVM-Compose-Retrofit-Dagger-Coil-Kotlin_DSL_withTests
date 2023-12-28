package com.shreyas.nytimes.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.ViewModelProvider
import com.shreyas.nytimes.model.RepositoryData
import com.shreyas.nytimes.ui.SearchList
import com.shreyas.nytimes.ui.SearchTopAppBar
import com.shreyas.nytimes.ui.theme.GithubRepositoryTheme
import com.shreyas.nytimes.viewmodel.GithubSearchViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    private lateinit var viewModel: GithubSearchViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this, viewModelFactory)[GithubSearchViewModel::class.java]

        // Compose Entry, set action bar based search view
        setContent {
            GithubRepositoryTheme {
                Surface(color = MaterialTheme.colors.background) {
                    SearchViewActionBar(viewModel) {
                        startActivity(DetailedViewActivity.newIntent(this, it))
                    }
                }
            }
        }
    }
}

@Composable
fun SearchViewActionBar(
    viewModel: GithubSearchViewModel,
    navigateToDetailView: (RepositoryData) -> Unit
) {
    val searchState by viewModel.searchState
    val searchTextState by viewModel.searchTextState
    val searchResponseData by viewModel.gitHubSearchResponse.observeAsState()
    val isLoading by viewModel.isLoading

    Scaffold(
        topBar = {
            SearchTopAppBar(
                viewModel = viewModel,
                searchState = searchState,
                searchTextState = searchTextState
            )
        },
        content = { padding ->
            SearchList(
                padding,
                searchResponseData?.items,
                isLoading,
                navigateToDetailView
            )
        }
    )
}