package com.example.mviexample.repository

import com.example.mviexample.api.PostsService
import com.example.mviexample.model.Post
import kotlinx.coroutines.Deferred
import retrofit2.Response
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val service: PostsService) : Repository {
    override fun getPosts(): Deferred<Response<List<Post>>> = service.getPostsAsync()
}