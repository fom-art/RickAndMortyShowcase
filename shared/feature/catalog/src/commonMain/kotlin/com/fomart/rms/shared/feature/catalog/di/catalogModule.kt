package com.fomart.rms.shared.feature.catalog.di

import com.fomart.rms.shared.feature.catalog.presentation.CatalogViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val catalogModule = module {
    viewModelOf(::CatalogViewModel)
}