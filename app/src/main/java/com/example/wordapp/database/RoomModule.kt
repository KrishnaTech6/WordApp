package com.example.wordapp.database

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Provides
    @Singleton
    fun provideWordsDatabase(application: Application): WordsDatabase {
        return Room.databaseBuilder(application, WordsDatabase::class.java, "words.db")
            .build()
    }

    @Provides
    fun provideWordDao(wordsDatabase: WordsDatabase): WordDAO {
        return wordsDatabase.wordDao()
    }
}