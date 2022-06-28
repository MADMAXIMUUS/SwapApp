package com.example.swap.ui.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.swap.ui.theme.Black
import com.example.swap.ui.theme.Light_brown

@Composable
fun SButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: ()->Unit
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults
            .buttonColors(
                backgroundColor = Light_brown
            )
    ) {
        Text(
            text = text,
            color = if (isSystemInDarkTheme()) {
                Black
            } else {
                Color.White
            },
            style = MaterialTheme.typography.button
        )
    }
}