package com.example.wordapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.wordapp.model.Word
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WordsViewModel @Inject
constructor(private val wordRepository: WordRepository) : ViewModel() {

    fun saveWord(word: String) = liveData {
        try {
            wordRepository.insertWord(Word(0, word))
            emit(true)
        } catch (e: Exception) {
            emit(false)
        }
    }

    fun deleteWord(word: Word) = liveData{
        try {
            wordRepository.deleteWord(word)
            emit(true)
        } catch (e: Exception) {
            emit(false)
        }
    }

    fun getAllWords() = wordRepository.getAllWords()


}