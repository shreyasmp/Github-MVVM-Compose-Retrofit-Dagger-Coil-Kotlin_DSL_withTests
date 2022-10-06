package com.shreyas.nytimes.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.ui.res.colorResource
import androidx.lifecycle.ViewModelProvider
import com.shreyas.nytimes.R
import com.shreyas.nytimes.ui.TopAppBarWithTitle
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
            Scaffold(
                topBar = { TopAppBarWithTitle() },
                backgroundColor = colorResource(id = R.color.purple_700)
            ) {
                /* Add code later */
            }
        }
    }
}