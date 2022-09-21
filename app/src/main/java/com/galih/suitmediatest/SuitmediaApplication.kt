package com.galih.suitmediatest

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SuitmediaApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: SuitmediaApplication
            private set
    }
}