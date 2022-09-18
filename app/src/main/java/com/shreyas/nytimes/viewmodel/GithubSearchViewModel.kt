package com.shreyas.nytimes.viewmodel

import android.util.Log
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shreyas.nytimes.model.RepositoryData
import com.shreyas.nytimes.repository.GitHubSearchRepository
import com.shreyas.nytimes.utils.ResultWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GithubSearchViewModel @Inject constructor(
    private val repository: GitHubSearchRepository
) : ViewModel() {

    companion object {
        private val TAG = GithubSearchViewModel::class.java.simpleName
    }

    @VisibleForTesting
    internal val _gitHubRepositoryList: MutableLiveData<MutableList<RepositoryData>> =
        MutableLiveData()
    val gitHubRepositoryList: LiveData<MutableList<RepositoryData>> = _gitHubRepositoryList

    val isError: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()

    fun fetchMostPopularGitHubRepos(repoSearchWord: String) {
        isLoading.value = true
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                repository.getMostPopularGitHubRepos(repoSearchWord)
            }
            when (result) {
                is ResultWrapper.SUCCESS -> {
                    isLoading.value = false
                    val gitHubData = result.value.value?.items
                    if (gitHubData != null && gitHubData.isNotEmpty()) {
                        isError.value = false
                        Log.d(TAG, "Repo List : $gitHubData")
                        _gitHubRepositoryList.value = gitHubData
                    } else {
                        isError.value = true
                    }
                }
                is ResultWrapper.FAILURE -> {
                    isLoading.value = false
                    isError.value = true
                    _gitHubRepositoryList.value = null
                }
            }
        }
    }
}