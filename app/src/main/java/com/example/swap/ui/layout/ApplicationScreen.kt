package com.example.swap.ui.layout

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.swap.navigation.NavigationTree
import com.example.swap.presentation.camerascreen.CameraScreen
import com.example.swap.ui.layout.login.LogInScreen
import com.example.swap.ui.layout.login.LoginViewModel
import com.example.swap.ui.layout.splash.SplashScreen

@Composable
fun ApplicationScreen() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavigationTree.Splash.name,
        modifier = Modifier
            .fillMaxSize()
    ) {
        composable(NavigationTree.Splash.name){
            SplashScreen(navController)
        }
        composable(NavigationTree.Home.name) {
            /*HomeScreen(
                mode, navController, advertViewModel
            )*/
        }
        composable(NavigationTree.Favorites.name) {
            /*FavoriteScreen(navController)*/
        }
        composable(NavigationTree.Drafts.name) {
            /*DraftsScreen(navController, authViewModel)*/
        }
        composable(NavigationTree.Chats.name) {
            /*ChatsScreen(navController)*/
        }
        composable(NavigationTree.Profile.name) {
            /*ProfileScreen(navController, authViewModel, userViewModel)*/
        }
        composable("new_advert") {
            /*NewAdvertScreen(navController, advertViewModel)*/
        }
        composable("signIn") {
            /*SignInScreen(navController, authViewModel)*/
        }
        composable(NavigationTree.Login.name) {
            val loginViewModel = hiltViewModel<LoginViewModel>()
            LogInScreen(navController, loginViewModel)
        }
        composable("camera") {
            CameraScreen(navController)
        }
        /*composable(
            "chat/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                }
            )
        ) {
            val id = remember {
                it.arguments?.getString("id").toString()
            }
            ChatScreen(id)
        }
        composable(
            "advert/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                }
            )
        ) {
            val id = remember {
                it.arguments?.getString("id").toString()
            }
            AdvertScreen(id)
        }*/
    }
}