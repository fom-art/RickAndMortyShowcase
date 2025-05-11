package com.fomart.rms.shared.feature.catalog.favorite.browse.di

import com.fomart.rms.shared.feature.catalog.all.browse.favorites.FavoritesViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val favoritesModule = module {
    viewModelOf(::FavoritesViewModel)
}