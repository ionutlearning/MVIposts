package com.example.mviexample.domain.result

import com.example.mviexample.model.Post

sealed class HomeResult : Result {
    object Loading : HomeResult()
    data class Success(val items: List<Post>) : HomeResult()
    data class Failure(val error: String) : HomeResult()
}