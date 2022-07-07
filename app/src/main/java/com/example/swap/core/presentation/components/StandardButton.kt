package com.example.swap.core.presentation.components

import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.swap.core.presentation.ui.theme.SwapTheme

@Composable
fun StandardButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    progressIndicator: Boolean = false
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults
            .buttonColors(
                backgroundColor = SwapTheme.colors.buttonBackgroundColor
            ),
    ) {
        if (progressIndicator)
            CircularProgressIndicator(
                color = SwapTheme.colors.buttonTextColor
            )
        else
            Text(
                text = text,
                color = SwapTheme.colors.buttonTextColor,
                style = SwapTheme.types.buttonText
            )
    }
}