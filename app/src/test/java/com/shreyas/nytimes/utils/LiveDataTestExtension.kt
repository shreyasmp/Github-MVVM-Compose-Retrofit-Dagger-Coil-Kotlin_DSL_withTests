package com.shreyas.nytimes.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.shreyas.nytimes.model.GitHubSearchResponse

fun <T> LiveData<T?>.testObserver() = TestObserver<T?>().also { testObserver ->
    observeForever(testObserver)
}