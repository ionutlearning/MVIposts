package com.example.mviexample.dispatcher

import androidx.lifecycle.LiveData
import com.example.mviexample.domain.action.Action
import com.example.mviexample.domain.result.Result
import com.example.mviexample.repository.Repository
import javax.inject.Inject

interface Dispatcher {
    fun dispatchAction(action: Action): LiveData<Result>
}