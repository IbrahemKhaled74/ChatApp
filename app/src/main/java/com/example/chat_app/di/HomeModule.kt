package com.example.chat_app.di

import com.example.chat_app.ui.home.RoomAdaptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object HomeModule {
    @Provides
    fun provideRoomAdaptor(): RoomAdaptor {
        return RoomAdaptor(null)
    }
}