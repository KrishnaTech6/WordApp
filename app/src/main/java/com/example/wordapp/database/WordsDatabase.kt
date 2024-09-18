package com.example.wordapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.wordapp.model.Word


@Database(entities = [Word::class], version = 1, exportSchema = false)
abstract class WordsDatabase: RoomDatabase() {
    abstract fun wordDao(): WordDAO
}