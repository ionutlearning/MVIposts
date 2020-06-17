package com.example.mviexample.di

import com.example.mviexample.ui.home.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun homeFragment(): HomeFragment
}