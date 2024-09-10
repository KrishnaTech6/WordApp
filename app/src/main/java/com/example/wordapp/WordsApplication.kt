package com.example.wordapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class WordsApplication:Application() {
    override fun onCreate() {
        super.onCreate()

    }
}