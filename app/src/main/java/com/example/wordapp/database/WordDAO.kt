package com.example.wordapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.wordapp.model.Word


@Dao
interface WordDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWord(word: Word)

    @Query("SELECT * FROM words")
    fun getAllWords(): LiveData<List<Word>>

    @Delete
    suspend fun deleteWord(word:Word)

    @Update
    suspend fun updateWord(word: Word)

}