package com.example.mviexample.domain.action

sealed class DetailsAction : Action {
    data class FetchRemoteData(val id: Int) : DetailsAction()
}