package com.example.mviexample.api

import com.example.mviexample.model.Post
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface PostsService {

    companion object {
        const val POSTS_URL = "/posts"
    }

    @GET(POSTS_URL)
    fun getPostsAsync(): Deferred<Response<List<Post>>>
}