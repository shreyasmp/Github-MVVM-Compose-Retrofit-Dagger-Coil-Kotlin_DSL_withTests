package com.shreyas.nytimes.model

import java.io.Serializable

data class RepositoryData(
    val name: String,
    val description: String,
    val stargazers_count: Int,
    val forks_count: Int,
    val updated_at: String,
    val html_url: String,
    val owner: OwnerData
) : Serializable
