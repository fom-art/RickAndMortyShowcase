package com.fomart.mafiamaster.di

import com.fomart.rms.shared.feature.catalog.all.search.di.searchModule
import com.fomart.rms.shared.feature.catalog.di.catalogModule
import com.fomart.rms.shared.feature.character_details.di.characterDetailsModule
import com.fomart.rms.shared.feature.favorites.di.favoritesModule
import org.koin.dsl.module

val featureModule = module {
    includes(
        catalogModule,
        characterDetailsModule,
        favoritesModule,
        searchModule
    )
}