package com.example.mviexample.di

import com.example.mviexample.repository.Repository
import com.example.mviexample.repository.PostsRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindRepository(impl: PostsRepositoryImpl): Repository
}