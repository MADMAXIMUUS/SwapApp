package com.example.swap.ui.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.swap.ui.theme.Card_Background
import com.example.swap.ui.theme.Deep_dark_blue
import com.example.swap.ui.theme.Light_Gray
import com.example.swap.ui.theme.Yellow

@Composable
fun STextField(
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions,
    isSingleLine: Boolean = false,
    value: String,
    label: String,
    keyboardActions: KeyboardActions,
    isSecureText: Boolean = false,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        modifier = modifier,
        value = value,
        shape = RoundedCornerShape(10.dp),
        colors = TextFieldDefaults.textFieldColors(
            textColor = if (isSystemInDarkTheme()) {
                Color.White
            } else {
                Deep_dark_blue
            },
            backgroundColor = if (isSystemInDarkTheme()) {
                Card_Background
            } else {
                Light_Gray
            },
            unfocusedLabelColor = if (isSystemInDarkTheme()) {
                Yellow
            } else {
                Deep_dark_blue
            },
            focusedLabelColor = if (isSystemInDarkTheme()) {
                Yellow
            } else {
                Deep_dark_blue
            },
            focusedIndicatorColor = if (isSystemInDarkTheme()) {
                Yellow
            } else {
                Deep_dark_blue
            },
            unfocusedIndicatorColor = if (isSystemInDarkTheme()) {
                if (value=="")
                    Card_Background
                else
                    Yellow
            } else {
                if (value=="")
                    Light_Gray
                else
                    Deep_dark_blue
            },
            cursorColor = if (isSystemInDarkTheme()) {
                Yellow
            } else {
                Deep_dark_blue
            }
        ),
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        textStyle = MaterialTheme.typography.h2,
        singleLine = isSingleLine,
        onValueChange = onValueChange,
        visualTransformation = if (isSecureText)
            PasswordVisualTransformation()
        else
            VisualTransformation.None,
        label = {
            Text(
                text = label,
                fontSize = 22.sp
            )
        }
    )
}