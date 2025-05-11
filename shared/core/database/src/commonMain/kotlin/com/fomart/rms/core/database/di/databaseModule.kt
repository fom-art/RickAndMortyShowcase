package com.fomart.rms.core.database.di

import com.fomart.rms.core.database.FavoritesDaoImpl
import com.fomart.rms.core.database.FavouriteQueries
import com.fomart.rms.core.database.createDatabase
import com.fomart.rms.core.database.domain.FavoritesDao
import org.koin.dsl.module

val databaseModule = module {
    includes(databaseDriverFactoryModule)
    single<FavouriteQueries> { createDatabase(databaseDriverFactory = get()).favouriteQueries }
    single<FavoritesDao> { FavoritesDaoImpl(queries = get()) }
}