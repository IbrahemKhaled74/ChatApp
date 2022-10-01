package com.example.chat_app.di

import com.example.chat_app.ui.createRoom.SpinnerAdaptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object CreatRoomModule {


    @Provides
    fun provideSpinnerAdaptor(): SpinnerAdaptor {
        return SpinnerAdaptor(null)
    }


}
