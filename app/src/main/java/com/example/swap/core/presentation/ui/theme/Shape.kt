package com.example.swap.core.presentation.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Shape

data class SwapShapes(
    val swapShape: Shape,
    val cardTagShape: Shape,
    val squareCardPhotoShape: Shape,
    val photoShape: Shape
)


val LocalSwapShapes = staticCompositionLocalOf<SwapShapes> {
    error("No shapes provided")
}