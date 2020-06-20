package com.example.mviexample.di

import com.example.mviexample.dispatcher.DetailsDispatcherImpl
import com.example.mviexample.dispatcher.Dispatcher
import com.example.mviexample.dispatcher.Dispatcher.Companion.DISPATCHER_DETAILS
import com.example.mviexample.dispatcher.Dispatcher.Companion.DISPATCHER_HOME
import com.example.mviexample.dispatcher.HomeDispatcherImpl
import dagger.Binds
import dagger.Module
import javax.inject.Named

@Module
abstract class DispatcherModule {
    @Binds
    @Named(DISPATCHER_HOME)
    abstract fun bindHomeDispatcher(impl: HomeDispatcherImpl): Dispatcher

    @Binds
    @Named(DISPATCHER_DETAILS)
    abstract fun bindDetailsDispatcher(impl: DetailsDispatcherImpl): Dispatcher
}