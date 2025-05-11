package com.fomart.rms.shared.feature.catalog.all.root.di

import com.fomart.rms.shared.feature.catalog.all.browse.di.browseModule
import com.fomart.rms.shared.feature.catalog.all.search.di.searchModule
import org.koin.dsl.module

val catalogAllModule = module {
    includes(
        browseModule,
        searchModule
    )
}