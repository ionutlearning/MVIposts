package com.example.mviexample.dispatcher

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.mviexample.domain.action.Action
import com.example.mviexample.domain.action.DetailsAction
import com.example.mviexample.domain.result.DetailsResult
import com.example.mviexample.domain.result.Result
import com.example.mviexample.repository.Repository
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import javax.inject.Inject

class DetailsDispatcherImpl @Inject constructor(private val repository: Repository) : Dispatcher {

    companion object {
        const val TIMEOUT_LIMIT = 10000L
    }

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
                        return@withTimeout DetailsResult.Success(comments)
                    } else {
                        return@withTimeout DetailsResult.Failure
                    }
                }
            } catch (exception: CancellationException) {
                exception.printStackTrace()
                return@withContext DetailsResult.Failure
            }
        }
    }
}