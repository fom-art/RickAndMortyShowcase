package com.fomart.rms.shared.feature.catalog.all.browse.di

import com.fomart.rms.shared.feature.catalog.all.browse.presentation.BrowseViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val browseModule = module {
    viewModelOf(::BrowseViewModel)
}