package com.example.wordapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.wordapp.model.Word


@Dao
interface WordDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWord(word: Word)

    @Query("SELECT * FROM words")
    suspend fun getAllWords(): List<Word>
}