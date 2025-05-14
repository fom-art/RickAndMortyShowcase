package com.fomart.rms

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.fomart.mafiamaster.ui.App
import com.fomart.rms.core.designsystem.theme.RickyAndMortyShowcaseTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickyAndMortyShowcaseTheme {
                App()
            }
        }
    }
}