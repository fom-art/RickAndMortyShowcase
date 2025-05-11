package com.fomart.rms.core.data.di

import com.fomart.rms.core.database.di.databaseModule
import com.fomart.rms.core.network.di.networkModule
import org.koin.dsl.module

val dataModule = module {
    includes(
        networkModule,
        databaseModule
    )
}