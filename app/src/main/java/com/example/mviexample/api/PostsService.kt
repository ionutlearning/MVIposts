package com.example.mviexample.api

import com.example.mviexample.model.Photo
import com.example.mviexample.model.Post
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface PostsService {

    companion object {
        const val POSTS_URL = "/posts"
        const val PHOTOS_URL = "/photos"
    }

    @GET(POSTS_URL)
    fun getPostsAsync(): Deferred<Response<List<Post>>>

    @GET(PHOTOS_URL)
    fun getPhotosAsync(): Deferred<Response<List<Photo>>>
}