package com.houseofalgorithms.ayahofthemoment

import android.app.Application
import com.houseofalgorithms.ayahofthemoment.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AyahApp : Application() {
    override fun onCreate() {
        super.onCreate()
        
        startKoin {
            androidContext(this@AyahApp)
            modules(appModule)
        }
    }
} 