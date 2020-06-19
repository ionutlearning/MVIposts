package com.example.mviexample.repository

import com.example.mviexample.api.PostsService
import com.example.mviexample.model.Photo
import com.example.mviexample.model.Post
import kotlinx.coroutines.Deferred
import retrofit2.Response
import javax.inject.Inject

class PostsRepositoryImpl @Inject constructor(private val service: PostsService) : Repository {
    override fun getPostsAsync(): Deferred<Response<List<Post>>> = service.getPostsAsync()
    override fun getPhotosAsync(): Deferred<Response<List<Photo>>> = service.getPhotosAsync()
}