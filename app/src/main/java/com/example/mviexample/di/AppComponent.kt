package com.example.mviexample.di

import android.app.Application
import com.example.mviexample.MyApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ActivityModule::class,
    AndroidSupportInjectionModule::class,
    DispatcherModule::class,
    NetworkModule::class,
    RepositoryModule::class,
    ViewModelModule::class
])
interface AppComponent : AndroidInjector<MyApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: Application): Builder
        fun build(): AppComponent
    }
}