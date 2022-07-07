package com.example.swap.core.util

sealed class Screen(val route: String) {
    object SplashScreen : Screen("splash")
    object SignInScreen : Screen("signIn")
    object SignUpScreen : Screen("signUp")
    object ForgotScreen : Screen("forgot")
    object HomeScreen : Screen("home")
    object DetailsScreen : Screen("details")
    object FavoritesScreen : Screen("favorites")
    object DraftsScreen : Screen("drafts")
    object CreateAdvertScreen : Screen("newAdvert")
    object ChatsListScreen : Screen("chats")
    object ChatScreen : Screen("chat")
    object ProfileScreen : Screen("profile")
}