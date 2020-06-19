package com.example.mviexample.domain.result

import com.example.mviexample.model.Comment

sealed class DetailsResult  : Result {
    object Loading : DetailsResult()
    data class Success(val comments: List<Comment>) : DetailsResult()
    object Failure : DetailsResult()
}