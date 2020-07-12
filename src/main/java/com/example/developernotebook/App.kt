package com.example.developernotebook

import android.app.Application
import com.example.developernotebook.util.Prefs

val prefs: Prefs by lazy {
    Prefs(App.instance)
}

class App : Application() {
    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}