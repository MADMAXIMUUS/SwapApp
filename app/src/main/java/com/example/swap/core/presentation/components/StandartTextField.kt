package com.example.swap.core.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.swap.R
import com.example.swap.core.presentation.ui.theme.SwapTheme

@Composable
fun StandardTextField(
    modifier: Modifier = Modifier,
    text: String = "",
    hint: String = "",
    maxLength: Int = 400,
    error: String = "",
    singleLine: Boolean = true,
    maxLines: Int = 1,
    leadingIcon: ImageVector? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    isPasswordToggleDisplayed: Boolean = keyboardOptions.keyboardType == KeyboardType.Password,
    isPasswordVisible: Boolean = false,
    onPasswordToggleClick: (Boolean) -> Unit = {},
    onValueChange: (String) -> Unit,
    focusRequester: FocusRequester = FocusRequester()
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier)
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(75.dp)
                .focusRequester(focusRequester = focusRequester),
            value = text,
            onValueChange = {
                if (it.length <= maxLength) {
                    onValueChange(it)
                }
            },
            maxLines = maxLines,
            shape = SwapTheme.shapes.swapShape,
            trailingIcon = if (isPasswordToggleDisplayed) {
                val icon: @Composable () -> Unit = {
                    IconButton(
                        onClick = {
                            onPasswordToggleClick(!isPasswordVisible)
                        }
                    ) {
                        Icon(
                            modifier = Modifier
                                .size(22.dp),
                            imageVector = if (isPasswordVisible) {
                                ImageVector.vectorResource(id = R.drawable.ic_open_eye)
                            } else {
                                ImageVector.vectorResource(id = R.drawable.ic_closed_eye)
                            },
                            tint = SwapTheme.colors.barIconTintColor,
                            contentDescription = if (isPasswordVisible) {
                                stringResource(R.string.password_visible_content_description)
                            } else {
                                stringResource(R.string.password_hidden_content_description)
                            }
                        )
                    }
                }
                icon
            } else null,
            colors = TextFieldDefaults.textFieldColors(
                textColor = SwapTheme.colors.textFieldTextColor,
                backgroundColor = SwapTheme.colors.textFieldBackgroundColor,
                unfocusedLabelColor = SwapTheme.colors.textFieldLabelColor,
                focusedLabelColor = SwapTheme.colors.textFieldLabelColor,
                focusedIndicatorColor = SwapTheme.colors.textFieldBackgroundColor,
                unfocusedIndicatorColor = SwapTheme.colors.textFieldBackgroundColor,
                cursorColor = SwapTheme.colors.textFieldLabelColor
            ),
            keyboardOptions = keyboardOptions,
            textStyle = SwapTheme.types.textFieldText,
            singleLine = singleLine,
            leadingIcon = if (leadingIcon != null) {
                val icon: @Composable () -> Unit = {
                    Icon(
                        imageVector = leadingIcon,
                        contentDescription = null,
                        tint = SwapTheme.colors.barIconTintColor,
                        modifier = Modifier.size(25.dp)
                    )
                }
                icon
            } else null,
            isError = error != "",
            visualTransformation = if (!isPasswordVisible && isPasswordToggleDisplayed) {
                PasswordVisualTransformation()
            } else {
                VisualTransformation.None
            },
            label = {
                Text(
                    text = hint,
                    style = SwapTheme.types.textFieldLabel
                )
            }
        )
        if (error.isNotEmpty()) {
            Text(
                text = error,
                style = SwapTheme.types.textFieldError,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}