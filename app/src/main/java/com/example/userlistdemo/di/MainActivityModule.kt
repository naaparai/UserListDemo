package com.example.userlistdemo.di

import com.example.userlistdemo.UserAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object MainActivityModule {
    @Provides
    fun provideUserAdapter(): UserAdapter {
        return UserAdapter()
    }
}