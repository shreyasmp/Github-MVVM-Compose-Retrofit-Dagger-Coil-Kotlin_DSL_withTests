package com.shreyas.nytimes.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shreyas.nytimes.model.GitHubSearchResponse
import com.shreyas.nytimes.service.GitHubRepoService
import com.shreyas.nytimes.utils.ResultWrapper
import javax.inject.Inject
import javax.inject.Singleton

interface GitHubSearchRepository {
    suspend fun getMostPopularGitHubRepos(orgName: String): ResultWrapper<LiveData<GitHubSearchResponse>>
}

@Singleton
class GitHubSearchRepositoryImpl @Inject constructor(
    private val service: GitHubRepoService
) : GitHubSearchRepository {

    companion object {
        private val TAG = GitHubSearchRepositoryImpl::class.java.simpleName
    }

    override suspend fun getMostPopularGitHubRepos(orgName: String): ResultWrapper<LiveData<GitHubSearchResponse>> {
        val gitHubSearchResults = MutableLiveData<GitHubSearchResponse>()
        return try {
            val responseBody = service.fetchMostPopularRepos(orgName, "stars", 20)
            if (responseBody.isSuccessful && responseBody.body() != null) {
                gitHubSearchResults.postValue(responseBody.body())
                ResultWrapper.SUCCESS(gitHubSearchResults)
            } else {
                ResultWrapper.FAILURE(null)
            }
        } catch (exception: Exception) {
            Log.d(TAG, "Exception: ${exception.message}")
            ResultWrapper.FAILURE(null)
        }
    }
}