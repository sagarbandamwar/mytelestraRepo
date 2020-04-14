package com.example.livedata

import android.app.Application
import android.content.Context


// application class will return context of the application
class MainApplication : Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: MainApplication? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        // initialize for any
        // Use ApplicationContext.
        val context = MainApplication.applicationContext()
    }
}