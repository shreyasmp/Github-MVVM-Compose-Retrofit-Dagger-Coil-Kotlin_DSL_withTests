package com.shreyas.nytimes.service

import com.shreyas.nytimes.model.GitHubSearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *  Service interface for GitHub Repo
 */

interface GitHubRepoService {

    @GET("search/repositories")
    suspend fun fetchMostPopularRepos(
        @Query("q") searchQuery: String,
        @Query("sort") sort: String,
        @Query("per_page") perPage: Int
    ): Response<GitHubSearchResponse>
}