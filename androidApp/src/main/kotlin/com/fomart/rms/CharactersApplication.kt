package com.fomart.rms

import android.app.Application
import com.fomart.mafiamaster.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level.DEBUG

class CharactersApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            initKoin(applicationContext)
            androidContext(this@CharactersApplication)
            androidLogger(DEBUG)
            modules(appModule)
            printLogger()
        }
    }
}