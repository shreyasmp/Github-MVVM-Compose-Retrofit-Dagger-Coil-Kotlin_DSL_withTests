package com.shreyas.nytimes.utils

import com.shreyas.nytimes.model.OwnerData
import com.shreyas.nytimes.model.RepositoryData

object GithubRepositoryDataProvider {

    val repositoryData = RepositoryData(
        name = "okhttp",
        description = "Square’s meticulous HTTP client for the JVM, Android, and GraalVM.",
        stargazers_count = 44786,
        forks_count = 9275,
        updated_at = "2023-12-12T02:20:23Z",
        html_url = "https://github.com/square/okhttp",
        owner = OwnerData(avatar_url = "https://avatars.githubusercontent.com/u/82592?v=4")
    )
}