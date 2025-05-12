package com.fomart.rms

import com.fomart.mafiamaster.ui.RmsApp
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.fomart.rms.core.designsystem.theme.RickyAndMortyShowcaseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickyAndMortyShowcaseTheme {
                RmsApp()
            }
        }
    }
}