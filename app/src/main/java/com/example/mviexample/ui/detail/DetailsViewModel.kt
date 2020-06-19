package com.example.mviexample.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.liveData
import com.example.mviexample.dispatcher.Dispatcher
import com.example.mviexample.dispatcher.Dispatcher.Companion.DISPATCHER_DETAILS
import com.example.mviexample.domain.action.DetailsAction
import com.example.mviexample.domain.event.DetailsEvent
import com.example.mviexample.domain.event.Event
import com.example.mviexample.domain.result.DetailsResult
import com.example.mviexample.ui.base.BaseViewModel
import com.example.mviexample.ui.base.EventListener
import javax.inject.Inject
import javax.inject.Named

class DetailsViewModel @Inject constructor(@Named(DISPATCHER_DETAILS) private val dispatcher: Dispatcher) :
    BaseViewModel(), EventListener {

    private val viewState = DetailsViewState()
    var data: LiveData<DetailsViewState> = liveData { viewState }

    override fun onEvent(event: Event) {
        when (event) {
            is DetailsEvent.FetchData -> fetchData(event.id)
        }
    }

    private fun fetchData(id: Int) {
        data =
            Transformations.map(dispatcher.dispatchAction(DetailsAction.FetchRemoteData(id))) { result ->
                when (result) {
                    is DetailsResult.Loading -> viewState.copy(isLoading = true)
                    is DetailsResult.Success -> viewState.copy(comments = result.comments)
                    is DetailsResult.Failure -> viewState.copy(isError = true)
                    else -> viewState.copy(isError = true)
                }
            }
    }
}