package com.example.mviexample.ui.home

import com.example.mviexample.model.Post
import com.example.mviexample.ui.base.ViewState

data class HomeViewState(
    val isLoading: Boolean = false,
    val items: List<Post> = emptyList(),
    val error: Throwable? = null
) : ViewState