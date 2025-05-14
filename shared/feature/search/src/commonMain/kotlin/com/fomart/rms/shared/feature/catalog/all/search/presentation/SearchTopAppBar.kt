package com.fomart.rms.shared.feature.catalog.all.search.presentation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.fomart.rms.core.designsystem.components.BlurryBottomShadeBox
import org.jetbrains.compose.resources.stringResource
import rickandmortyshowcase.shared.feature.search.generated.resources.Res
import rickandmortyshowcase.shared.feature.search.generated.resources.search_characters

@Composable
fun SearchTopAppBar(
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit,
    clear: () -> Unit,
    navigateBack: () -> Unit
) {
    var query by remember { mutableStateOf("") }

    BlurryBottomShadeBox {
        TextField(
            modifier = modifier.padding(4.dp).fillMaxWidth(),
            value = query,
            onValueChange = { newValue ->
                query = newValue
                onSearch(newValue)
            },
            placeholder = {
                Text(
                    stringResource(Res.string.search_characters),
                    color = MaterialTheme.colorScheme.onBackground
                )
            },
            colors = TextFieldDefaults.colors().copy(
                focusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent

            ),
            leadingIcon = {
                IconButton(onClick = navigateBack) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        tint = MaterialTheme.colorScheme.onBackground,
                        contentDescription = "Go Back"
                    )
                }
            },
            trailingIcon = {
                IconButton(onClick = clear) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        tint = MaterialTheme.colorScheme.onBackground,
                        contentDescription = "Clean"
                    )
                }
            }
        )
    }
}