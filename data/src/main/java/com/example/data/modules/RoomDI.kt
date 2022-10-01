package com.example.data.modules

import com.example.data.repos.room.RoomOnlineDataSourceImpl
import com.example.data.repos.room.RoomRepositoryImpl
import com.example.domain.repos.RoomOnlineDataSource
import com.example.domain.repos.RoomRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RoomDI {

    @Provides
    fun provideRoomRepository(roomOnlineDataSource: RoomOnlineDataSource)
            : RoomRepository {
        return RoomRepositoryImpl(roomOnlineDataSource)
    }

    @Provides
    fun provideRoomOnlineDataSource(): RoomOnlineDataSource {
        return RoomOnlineDataSourceImpl()
    }
}