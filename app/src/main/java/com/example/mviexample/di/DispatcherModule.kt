package com.example.mviexample.di

import com.example.mviexample.dispatcher.Dispatcher
import com.example.mviexample.dispatcher.HomeDispatcherImpl
import dagger.Binds
import dagger.Module

@Module
abstract class DispatcherModule {
    @Binds
    abstract fun bindHomeDispatcher(impl: HomeDispatcherImpl): Dispatcher
}