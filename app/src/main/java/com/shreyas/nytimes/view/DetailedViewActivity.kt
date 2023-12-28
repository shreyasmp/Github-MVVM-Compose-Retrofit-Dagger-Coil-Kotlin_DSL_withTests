package com.shreyas.nytimes.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.shreyas.nytimes.model.RepositoryData
import com.shreyas.nytimes.ui.theme.GithubRepositoryTheme

class DetailedViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GithubRepositoryTheme {

            }
        }
    }

    companion object {
        private const val REPO_DATA = "repo_data"
        fun newIntent(context: Context, repositoryData: RepositoryData) =
            Intent(context, DetailedViewActivity::class.java).apply {
                putExtra(REPO_DATA, repositoryData)
            }
    }
}