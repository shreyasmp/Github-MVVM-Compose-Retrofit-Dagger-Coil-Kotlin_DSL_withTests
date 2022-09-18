package com.shreyas.nytimes.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.navigation.ui.AppBarConfiguration
import com.shreyas.nytimes.view.theme.GitHubRepoComposeAppTheme
import dagger.android.AndroidInjection

class MainActivity : ComponentActivity() {

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }

    private lateinit var appBarConfiguration: AppBarConfiguration


    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContent {
            GitHubRepoComposeAppTheme {
                GitHubRepoRecyclerviewComposable()
            }
        }
    }

    @Composable
    internal fun GitHubRepoRecyclerviewComposable() {
        LazyColumn {

        }
    }
}