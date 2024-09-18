package com.example.wordapp.di

import com.example.wordapp.WordRepository
import com.example.wordapp.WordRepositoryImpl
import com.example.wordapp.database.WordDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)

object WordModule {
    @Provides
    fun provideWordRepository(wordDAO: WordDAO): WordRepository {
        return WordRepositoryImpl(wordDAO)
    }
}