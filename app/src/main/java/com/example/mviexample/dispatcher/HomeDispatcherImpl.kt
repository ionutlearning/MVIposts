package com.example.mviexample.dispatcher

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.mviexample.domain.action.Action
import com.example.mviexample.domain.action.HomeAction
import com.example.mviexample.domain.result.HomeResult
import com.example.mviexample.domain.result.Result
import com.example.mviexample.repository.Repository
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import javax.inject.Inject

class HomeDispatcherImpl @Inject constructor(private val repository: Repository) : Dispatcher {

    companion object {
        const val TIMEOUT_LIMIT = 10000L
    }

    override fun dispatchAction(action: Action): LiveData<Result> = liveData {
        when (action) {
            is HomeAction.FetchRemoteData -> {
                emit(HomeResult.Loading)
                emit(fetchRemotePosts())
            }
        }
    }

    private suspend fun fetchRemotePosts(): Result {
        return withContext(IO) {
            try {
                withTimeout(TIMEOUT_LIMIT) {
                    val response = repository.getPostsAsync().await()
                    val body = response.body() ?: emptyList()
                    if (response.isSuccessful && body.isNotEmpty()) {
                        return@withTimeout HomeResult.Success(body)
                    } else {
                        return@withTimeout HomeResult.Failure
                    }
                }
            } catch (exception: CancellationException) {
                return@withContext HomeResult.Failure
            }
        }
    }
}