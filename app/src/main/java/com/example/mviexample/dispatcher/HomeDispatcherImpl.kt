package com.example.mviexample.dispatcher

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.mviexample.domain.action.Action
import com.example.mviexample.domain.action.HomeAction
import com.example.mviexample.domain.result.HomeResult
import com.example.mviexample.domain.result.Result
import com.example.mviexample.repository.Repository
import javax.inject.Inject

class HomeDispatcherImpl @Inject constructor(private val repository: Repository) : Dispatcher {

    override fun dispatchAction(action: Action): LiveData<Result> = liveData {
        when (action) {
            is HomeAction.FetchRemoteData -> {
                emit(HomeResult.Loading)
                emit(fetchRemotePosts())
            }
        }
    }

    private suspend fun fetchRemotePosts(): Result {
        return try {
            val response = repository.getPostsAsync().await()
            val body = response.body() ?: emptyList()
            if (response.isSuccessful && body.isNotEmpty()) {
                HomeResult.Success(body)
            } else {
                HomeResult.Failure("aici123 error")
            }
        } catch (e: Exception) {
            HomeResult.Failure("aici123 error")
        }
    }
}