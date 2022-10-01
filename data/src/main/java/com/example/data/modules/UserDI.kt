package com.example.data.modules

import com.example.data.repos.user.UserOnlineDataSourceImpl
import com.example.data.repos.user.userRepositoryImpl
import com.example.domain.repos.UserOnlineDataSource
import com.example.domain.repos.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UserDI {
    @Provides
    fun provideUserRepository(userOnlineDataSource: UserOnlineDataSource)
            : UserRepository {
        return userRepositoryImpl(userOnlineDataSource)

    }

    @Provides
    fun provideUserOnlineDataSource()
            : UserOnlineDataSource {
        return UserOnlineDataSourceImpl()
    }
}