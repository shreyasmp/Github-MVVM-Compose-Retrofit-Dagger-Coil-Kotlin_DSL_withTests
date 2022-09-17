package com.shreyas.nytimes.viewmodel

import androidx.lifecycle.ViewModel
import com.shreyas.nytimes.repository.GitHubSearchRepositoryImpl
import javax.inject.Inject

class GithubSearchViewModel @Inject constructor(
    private val repository: GitHubSearchRepositoryImpl
) : ViewModel() {

    companion object {
        private val TAG = GithubSearchViewModel::class.java.simpleName
    }


}