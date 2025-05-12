package com.fomart.rms.shared.feature.catalog.all.search.di

import com.fomart.rms.shared.feature.catalog.all.search.presentation.SearchViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val searchModule = module {
    viewModelOf(::SearchViewModel)
}