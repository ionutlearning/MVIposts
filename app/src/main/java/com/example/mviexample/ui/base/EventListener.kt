package com.example.mviexample.ui.base

import com.example.mviexample.domain.event.Event

interface EventListener {
    fun onEvent(event: Event)
}