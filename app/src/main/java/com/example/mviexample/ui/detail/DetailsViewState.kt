package com.example.mviexample.ui.detail

import com.example.mviexample.model.Comment
import com.example.mviexample.ui.base.ViewState

data class DetailsViewState (
    val isLoading: Boolean = false,
    val comments: List<Comment> = emptyList(),
    val isError: Boolean = false
) : ViewState