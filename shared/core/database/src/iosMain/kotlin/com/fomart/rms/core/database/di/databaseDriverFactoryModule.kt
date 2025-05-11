package com.fomart.rms.core.database.di

import com.fomart.rms.core.database.DatabaseDriverFactory
import org.koin.dsl.module

internal actual val databaseDriverFactoryModule = module {
    single<DatabaseDriverFactory>{ DatabaseDriverFactory()
    }
}