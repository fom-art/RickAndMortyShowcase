package com.fomart.rms.shared.feature.character_details.di

import com.fomart.rms.shared.feature.character_details.presentation.CharacterDetailsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val characterDetailsModule = module {
    viewModelOf(::CharacterDetailsViewModel)
}