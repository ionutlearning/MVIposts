package com.example.mviexample.ui.home

import androidx.lifecycle.MutableLiveData
import com.example.mviexample.repository.Repository
import com.example.mviexample.ui.base.BaseViewModel
import kotlinx.coroutines.*
import java.lang.RuntimeException
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val repository: Repository) : BaseViewModel() {

    val viewState = HomeViewState()
    val data = MutableLiveData<HomeViewState>()

    fun getPosts() {
        runBlocking {
            coroutineScope {
                data.value = viewState.copy(isLoading = true)
                val posts = repository.getPosts()
                val response = posts.await()
                if (response.isSuccessful) {
                    data.value = viewState.copy(items = response.body())
                } else {
                    data.value = viewState.copy(error = RuntimeException("xxx"))
                }
            }
        }
    }
}