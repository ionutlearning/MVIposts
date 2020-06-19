package com.example.mviexample.domain.event

sealed class DetailsEvent : Event {
    data class FetchData(val id: Int) : DetailsEvent()
}