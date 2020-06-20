package com.example.mviexample.di

import com.example.mviexample.repository.Repository
import com.example.mviexample.repository.RepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindRepository(impl: RepositoryImpl): Repository
}