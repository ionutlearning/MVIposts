package com.example.mviexample.repository

import com.example.mviexample.model.Comment
import com.example.mviexample.model.Photo
import com.example.mviexample.model.Post
import kotlinx.coroutines.Deferred
import retrofit2.Response

interface Repository {

    companion object {
        const val TIMEOUT_LIMIT = 5000L
    }

    fun getPostsAsync(): Deferred<Response<List<Post>>>
    fun getPhotosAsync(): Deferred<Response<List<Photo>>>
    fun getCommentsAsync(id: Int): Deferred<Response<List<Comment>>>
}