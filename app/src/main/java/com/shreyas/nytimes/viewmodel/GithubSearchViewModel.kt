package com.shreyas.nytimes.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shreyas.nytimes.model.GitHubSearchResponse
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

    @VisibleForTesting
    internal val _searchState: MutableState<SearchState> = mutableStateOf(SearchState.CLOSED)
    val searchState: State<SearchState> = _searchState

    @VisibleForTesting
    internal val _searchTextState: MutableState<String> = mutableStateOf("")
    val searchTextState: State<String> = _searchTextState

    @VisibleForTesting
    internal val _gitHubSearchResponse: MutableLiveData<GitHubSearchResponse?> =
        MutableLiveData()
    val gitHubSearchResponse: LiveData<GitHubSearchResponse?> = _gitHubSearchResponse

    val isError: MutableState<Boolean> = mutableStateOf(false)
    val isLoading: MutableState<Boolean> = mutableStateOf(false)

    fun fetchMostPopularGitHubRepos(repoSearchWord: String) {
        isLoading.value = true
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                repository.getMostPopularGitHubRepos(repoSearchWord)
            }
            when (result) {
                is ResultWrapper.SUCCESS -> {
                    isError.value = false
                    _gitHubSearchResponse.value = result.value.value
                }

                is ResultWrapper.FAILURE -> {
                    isError.value = true
                    _gitHubSearchResponse.value = null
                }
            }
            isLoading.value = false
        }
    }

    fun updateSearchState(newRepoName: SearchState) {
        _searchState.value = newRepoName
    }

    fun updateSearchTextState(newRepoName: String) {
        _searchTextState.value = newRepoName
    }
}