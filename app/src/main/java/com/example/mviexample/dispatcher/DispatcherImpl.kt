package com.example.mviexample.dispatcher

import androidx.lifecycle.LiveData
import com.example.mviexample.domain.action.Action
import com.example.mviexample.domain.result.Result
import com.example.mviexample.repository.Repository
import javax.inject.Inject

class DispatcherImpl @Inject constructor(private val repository: Repository): Dispatcher {
//    override fun dispatchAction(action: Action): LiveData<Result> {
//
//    }
}