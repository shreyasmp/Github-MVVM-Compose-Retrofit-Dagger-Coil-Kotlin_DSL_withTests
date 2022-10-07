package com.shreyas.nytimes.viewmodel

import android.util.Log
import androidx.annotation.VisibleForTesting
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shreyas.nytimes.model.RepositoryData
import com.shreyas.nytimes.repository.GitHubSearchRepository
import com.shreyas.nytimes.utils.ResultWrapper
import com.shreyas.nytimes.utils.SearchState
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
    internal val _searchState: MutableState<SearchState> = mutableStateOf(SearchState.CLOSED)
    val searchState: State<SearchState> = _searchState

    @VisibleForTesting
    internal val _searchTextState: MutableState<String> = mutableStateOf("")
    val searchTextState: State<String> = _searchTextState

    @VisibleForTesting
    internal val _gitHubRepositoryList: MutableLiveData<MutableList<RepositoryData>?> =
        MutableLiveData()
    val gitHubRepositoryList: LiveData<MutableList<RepositoryData>?> = _gitHubRepositoryList

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

    fun updateSearchState(newRepoName: SearchState) {
        _searchState.value = newRepoName
    }

    fun updateSearchTextState(newRepoName: String) {
        _searchTextState.value = newRepoName
    }
}