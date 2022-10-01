package com.example.data.modules

import com.example.domain.models.Category
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class CategoryDI {
    @Provides
    fun provideCategory(): Category {
        return Category()
    }
}