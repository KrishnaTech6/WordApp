package com.example.wordapp

import androidx.lifecycle.LiveData
import com.example.wordapp.model.Word

interface WordRepository {

    suspend fun insertWord(word: Word)

    fun getAllWords(): LiveData<List<Word>>

}