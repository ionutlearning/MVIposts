package com.example.mviexample.repository

import com.example.mviexample.model.Post
import kotlinx.coroutines.Deferred
import retrofit2.Response

interface Repository {
    fun getPostsAsync(): Deferred<Response<List<Post>>>
}