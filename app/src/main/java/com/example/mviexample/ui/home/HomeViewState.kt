package com.example.mviexample.ui.home

import com.example.mviexample.model.Photo
import com.example.mviexample.model.Post
import com.example.mviexample.ui.base.ViewState

data class HomeViewState(
    val isLoading: Boolean = false,
    val posts: List<Post> = emptyList(),
    val photos: List<Photo> = emptyList(),
    val isError: Boolean = false
) : ViewState