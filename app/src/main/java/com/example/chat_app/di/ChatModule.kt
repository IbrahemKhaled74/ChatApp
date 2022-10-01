package com.example.chat_app.di

import com.example.chat_app.ui.chat.ChatAdaptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ChatModule {
    @Provides
    fun provideChatAdaptor(): ChatAdaptor {
        return ChatAdaptor()
    }
}