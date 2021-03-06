package com.example.mviexample.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.liveData
import com.example.mviexample.dispatcher.Dispatcher
import com.example.mviexample.dispatcher.Dispatcher.Companion.DISPATCHER_HOME
import com.example.mviexample.domain.action.HomeAction
import com.example.mviexample.domain.event.Event
import com.example.mviexample.domain.event.HomeEvent
import com.example.mviexample.domain.result.HomeResult
import com.example.mviexample.ui.base.BaseViewModel
import com.example.mviexample.ui.base.EventListener
import javax.inject.Inject
import javax.inject.Named

class HomeViewModel @Inject constructor(@Named(DISPATCHER_HOME) private val dispatcher: Dispatcher) :
    BaseViewModel(), EventListener {

    private val viewState = HomeViewState()
    var data: LiveData<HomeViewState> = liveData { viewState }

    override fun onEvent(event: Event) {
        when (event) {
            is HomeEvent.FetchData -> fetchData()
        }
    }

    private fun fetchData() {
        data =
            Transformations.map(dispatcher.dispatchAction(HomeAction.FetchRemoteData)) { result ->
                when (result) {
                    is HomeResult.Loading -> viewState.copy(isLoading = true)
                    is HomeResult.Success -> viewState.copy(
                        posts = result.posts,
                        photos = result.photos
                    )
                    is HomeResult.Failure -> viewState.copy(isError = true)
                    else -> viewState.copy(isError = true)
                }
            }
    }
}