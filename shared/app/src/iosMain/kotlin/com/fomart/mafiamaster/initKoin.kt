package com.fomart.mafiamaster

import com.fomart.mafiamaster.di.appModule
import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules(appModule)
    }
}