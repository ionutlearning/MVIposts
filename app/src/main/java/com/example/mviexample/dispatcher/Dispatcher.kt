package com.example.mviexample.dispatcher

import androidx.lifecycle.LiveData
import com.example.mviexample.domain.action.Action
import com.example.mviexample.domain.result.Result
import com.example.mviexample.repository.Repository
import javax.inject.Inject

interface Dispatcher {

    companion object {
        const val DISPATCHER_HOME = "dispatcher_home"
        const val DISPATCHER_DETAILS = "dispatcher_details"
    }

    fun dispatchAction(action: Action): LiveData<Result>
}