package com.example.mviexample.domain.event

sealed class HomeEvent : Event {
    object FetchData : HomeEvent()
}