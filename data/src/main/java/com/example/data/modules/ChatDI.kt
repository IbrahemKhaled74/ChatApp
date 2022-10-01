package com.example.data.modules

import com.example.data.repos.chat.ChatOnlineDataSourceImpl
import com.example.data.repos.chat.ChatRepositoryImpl
import com.example.domain.repos.ChatOnlineDataSource
import com.example.domain.repos.ChatRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ChatDI {
    @Provides
    fun provideChatOnlineDatasource(): ChatOnlineDataSource {
        return ChatOnlineDataSourceImpl()
    }

    @Provides
    fun provideChatRepository(chatOnlineDataSource: ChatOnlineDataSource)
            : ChatRepository {
        return ChatRepositoryImpl(chatOnlineDataSource)
    }
}