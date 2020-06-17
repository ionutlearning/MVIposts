package com.example.mviexample.dispatcher

import androidx.lifecycle.LiveData
import com.example.mviexample.domain.action.Action
import com.example.mviexample.domain.result.Result

class DispatcherImpl : Dispatcher {
    override fun dispatchAction(action: Action): LiveData<Result> {
        TODO("Not yet implemented")
    }
}