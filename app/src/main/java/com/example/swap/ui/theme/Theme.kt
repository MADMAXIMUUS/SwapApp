package com.example.swap.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Dark_Bar,
    primaryVariant= Dark_Bar,
    surface= Dark_Background,
    background = Dark_Background
)

private val LightColorPalette = lightColors(
    primary = Color.White,
    primaryVariant = Color.White,
    background = Color.White,
    surface = Color.White
)

@Composable
fun SwapTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
            colors = colors,
            typography = Typography,
            shapes = Shapes,
            content = content
    )
}