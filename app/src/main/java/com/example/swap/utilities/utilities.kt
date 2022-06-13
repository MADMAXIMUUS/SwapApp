package com.example.swap.utilities

import android.widget.Toast
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun showSnackBar(message: String, scaffoldState: ScaffoldState, scope: CoroutineScope){
    scope.launch {
        scaffoldState.snackbarHostState.showSnackbar(message = message)
    }
}

@Composable
fun ShowToast(message: String){
    Toast.makeText(LocalContext.current, message,Toast.LENGTH_LONG).show()
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun HideKeyboard() {
    val keyboardController = LocalSoftwareKeyboardController.current
    keyboardController?.hide()
}