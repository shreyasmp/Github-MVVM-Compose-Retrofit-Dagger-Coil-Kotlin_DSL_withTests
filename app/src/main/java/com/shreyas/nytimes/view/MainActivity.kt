package com.shreyas.nytimes.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.lifecycle.ViewModelProvider
import com.shreyas.nytimes.ui.SearchViewActionBar
import com.shreyas.nytimes.ui.theme.GitHubActionSearchAppBarTheme
import com.shreyas.nytimes.ui.theme.Purple700
import com.shreyas.nytimes.viewmodel.GithubSearchViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }

    private lateinit var viewModel: GithubSearchViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this, viewModelFactory)[GithubSearchViewModel::class.java]

        setContent {
            GitHubActionSearchAppBarTheme {
                Surface(color = Purple700) {
                    SearchViewActionBar(viewModel)
                }
            }
        }
    }
}