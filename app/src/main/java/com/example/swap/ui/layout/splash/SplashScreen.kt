package com.example.swap.ui.layout.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import com.example.swap.navigation.NavigationTree

@Composable
fun SplashScreen(navController: NavController) {
    LaunchedEffect(
        key1 = Unit,
        block = {
            navController.navigate(NavigationTree.Login.name)
        }
    )
}