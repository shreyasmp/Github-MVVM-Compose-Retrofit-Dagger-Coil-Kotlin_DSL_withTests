package com.shreyas.nytimes.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.lifecycle.ViewModelProvider
import com.shreyas.nytimes.ui.SearchViewActionBar
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
                    SearchViewActionBar(viewModel)
                }
            }
        }
    }
}