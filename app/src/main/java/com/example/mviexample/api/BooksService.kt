package com.example.mviexample.api

import com.example.mviexample.model.Book
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface BooksService {
    @GET("")
    fun getBooksAsync(): Deferred<Response<List<Book>>>
}