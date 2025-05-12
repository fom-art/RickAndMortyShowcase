package com.fomart.mafiamaster.di

import com.fomart.rms.core.data.di.dataModule
import org.koin.dsl.module

val appModule = module {
    includes(
        dataModule,
        featureModule
    )
}