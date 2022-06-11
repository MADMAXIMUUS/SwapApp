package com.example.swap.utilities

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun showSnackBar(message: String, scaffoldState: ScaffoldState, scope: CoroutineScope){
    scope.launch {
        scaffoldState.snackbarHostState.showSnackbar(message = message)
    }
}
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun HideKeyboard() {
    val keyboardController = LocalSoftwareKeyboardController.current
    keyboardController?.hide()
}