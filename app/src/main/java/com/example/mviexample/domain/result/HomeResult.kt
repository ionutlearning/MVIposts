package com.example.mviexample.domain.result

import com.example.mviexample.model.Photo
import com.example.mviexample.model.Post

sealed class HomeResult : Result {
    object Loading : HomeResult()
    data class Success(val posts: List<Post>, val photos: List<Photo>) : HomeResult()
    object Failure : HomeResult()
}