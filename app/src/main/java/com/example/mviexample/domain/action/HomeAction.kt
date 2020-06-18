package com.example.mviexample.domain.action

sealed class HomeAction : Action {
    object FetchRemoteData : HomeAction()
}