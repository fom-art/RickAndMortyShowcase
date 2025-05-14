package com.fomart.mafiamaster

import androidx.compose.ui.window.ComposeUIViewController
import com.fomart.mafiamaster.ui.App
import com.fomart.rms.core.designsystem.theme.RickyAndMortyShowcaseTheme

fun MainViewController() = ComposeUIViewController {
    RickyAndMortyShowcaseTheme {
        App()
    }
}