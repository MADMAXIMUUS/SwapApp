package com.example.swap.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

private val DarkColorPalette = darkColors(
    primary = Dark_Bar,
    primaryVariant = Dark_Bar,
    surface = Dark_Content_Background,
    onBackground = White,
    secondary = Light_brown,
    onSurface = White,
    onSecondary = White,
    background = Black,
    error = Red,
    onPrimary = White
)

private val LightColorPalette = lightColors(
    primary = White,
    primaryVariant = White,
    background = White,
    onBackground = Black,
    surface = White,
    onSurface = Black,
    onPrimary = Deep_dark_blue,
    onSecondary = White,
    error = Red,
    secondary = Light_brown
)

@Composable
fun SwapTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    CompositionLocalProvider(LocalSpacing provides Spacing()) {
        MaterialTheme(
            colors = colors,
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }
}