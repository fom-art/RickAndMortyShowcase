package com.fomart.rms

import android.content.Context
import com.fomart.mafiamaster.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.KoinApplication
import org.koin.core.logger.Level

fun KoinApplication.initKoin(context: Context) {
    androidContext(context)
    androidLogger(Level.DEBUG)
    modules(appModule)
    printLogger()
}