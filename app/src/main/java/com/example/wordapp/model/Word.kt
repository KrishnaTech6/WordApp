package com.example.wordapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "words")
data class Word (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var word: String
)