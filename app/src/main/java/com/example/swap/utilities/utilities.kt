package com.example.swap.utilities

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import com.example.swap.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.io.File

fun showSnackBar(message: String, scaffoldState: ScaffoldState, scope: CoroutineScope){
    scope.launch {
        scaffoldState.snackbarHostState.showSnackbar(message = message)
    }
}

fun showToast(message: String, context: Context) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun HideKeyboard() {
    val keyboardController = LocalSoftwareKeyboardController.current
    keyboardController?.hide()
}

fun getDirectory(context: Context): File {
    val mediaDir = context.externalMediaDirs.firstOrNull()?.let {
        File(it, context.getString(R.string.app_name)).apply {
            mkdirs()
        }
    }
    return  if (mediaDir != null && mediaDir.exists())
        mediaDir else context.filesDir
}

/*
@OptIn(ExperimentalFoundationApi::class)
fun getGridSize(text: String): GridCells{

}*/
