package com.example.wordapp

import androidx.lifecycle.LiveData
import com.example.wordapp.database.WordDAO
import com.example.wordapp.model.Word

class WordRepositoryImpl(private val wordDAO: WordDAO):WordRepository {
    override suspend fun insertWord(word: Word) {
        wordDAO.insertWord(word)
    }

    override suspend fun deleteWord(word: Word) {
        wordDAO.deleteWord(word)
    }

    override  fun getAllWords(): LiveData<List<Word>> {
        return wordDAO.getAllWords()
    }

    override suspend fun updateWord(word: Word) {
        return wordDAO.updateWord(word)
    }
}