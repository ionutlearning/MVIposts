package com.example.mviexample.api

import com.example.mviexample.model.Comment
import com.example.mviexample.model.Photo
import com.example.mviexample.model.Post
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PostsService {

    companion object {
        const val POSTS_URL = "/posts"
        const val PHOTOS_URL = "/photos"
        const val COMMENTS_URL = "posts/{id}/comments"
    }

    @GET(POSTS_URL)
    fun getPostsAsync(): Deferred<Response<List<Post>>>

    @GET(PHOTOS_URL)
    fun getPhotosAsync(): Deferred<Response<List<Photo>>>

    @GET(COMMENTS_URL)
    fun getCommentsAsync(@Path("id") id: Int): Deferred<Response<List<Comment>>>
}