package com.fomart.rms.core.model.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource

sealed interface UiImageVector {
    data class DynamicImageVector(val value: ImageVector) : UiImageVector
    class ImageVectorResourceId(
        val id: DrawableResource,
    ) : UiImageVector

    @Composable
    fun asImageVector(): ImageVector {
        return when (this) {
            is DynamicImageVector -> value
            is ImageVectorResourceId -> vectorResource(id)
        }
    }
}