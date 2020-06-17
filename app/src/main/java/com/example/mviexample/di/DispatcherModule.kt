package com.example.mviexample.di

import com.example.mviexample.dispatcher.Dispatcher
import com.example.mviexample.dispatcher.DispatcherImpl
import dagger.Binds
import dagger.Module

@Module
abstract class DispatcherModule {
    @Binds
    abstract fun bindDispatcher(impl: DispatcherImpl): Dispatcher
}