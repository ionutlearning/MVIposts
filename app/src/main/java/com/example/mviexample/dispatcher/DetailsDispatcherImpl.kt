package com.example.mviexample.dispatcher

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.mviexample.domain.action.Action
import com.example.mviexample.domain.action.DetailsAction
import com.example.mviexample.domain.result.DetailsResult
import com.example.mviexample.domain.result.Result
import com.example.mviexample.repository.Repository
import com.example.mviexample.repository.Repository.Companion.TIMEOUT_LIMIT
import kotlinx.coroutines.*
import javax.inject.Inject

class DetailsDispatcherImpl @Inject constructor(private val repository: Repository) : Dispatcher {

    override fun dispatchAction(action: Action): LiveData<Result> = liveData {
        when (action) {
            is DetailsAction.FetchRemoteData -> {
                emit(DetailsResult.Loading)
                emit(fetchRemotePosts(action.id))
            }
        }
    }

    private suspend fun fetchRemotePosts(id: Int): Result {
        return withContext(Dispatchers.IO) {
            try {
                withTimeout(TIMEOUT_LIMIT) {
                    val response = repository.getCommentsAsync(id).await()
                    val comments = response.body() ?: emptyList()

                    if (response.isSuccessful) {
                        return@withTimeout DetailsResult.Success(comments.take(3))
                    } else {
                        return@withTimeout DetailsResult.Failure
                    }
                }
            } catch (exception: Exception) {
                exception.printStackTrace()
                return@withContext DetailsResult.Failure
            }
        }
    }
}