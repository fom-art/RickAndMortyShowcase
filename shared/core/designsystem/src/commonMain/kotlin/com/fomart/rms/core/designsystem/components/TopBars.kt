package com.fomart.rms.core.designsystem.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import org.jetbrains.compose.resources.stringResource
import rickandmortyshowcase.shared.core.designsystem.generated.resources.Res
import rickandmortyshowcase.shared.core.designsystem.generated.resources.go_back
import kotlin.math.max


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultTopBar(
    modifier: Modifier = Modifier,
    title: String = "",
    navigateBack: (() -> Unit)? = null,
    trailingIcon: @Composable () -> Unit = {}
) {
    BlurryBottomShadeBox {
        TopAppBar(
            modifier = modifier,
            colors = TopAppBarDefaults.topAppBarColors().copy(containerColor = Color.Transparent),
            title = {
                Text(
                    modifier = Modifier,
                    text = title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Medium
                )
            },
            navigationIcon = {
                if (navigateBack != null) {
                    IconButton(onClick = navigateBack) {
                        Icon(
                            tint = MaterialTheme.colorScheme.onPrimaryContainer,
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(Res.string.go_back)
                        )
                    }
                }
            },
            actions = {
                trailingIcon()
            }
        )
    }
}