package com.shreyas.nytimes.model

import java.io.Serializable

data class GitHubSearchResponse(
    val total_count: Int,
    val incomplete_results: Boolean,
    val items: MutableList<RepositoryData>
) : Serializable
