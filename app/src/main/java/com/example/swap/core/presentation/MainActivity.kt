package com.example.swap.core.presentation

import android.animation.ObjectAnimator
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.animation.AnticipateInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.swap.core.presentation.components.Navigation
import com.example.swap.core.presentation.components.StandardScaffold
import com.example.swap.core.presentation.ui.theme.SwapTheme
import com.example.swap.core.util.Screen
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@ExperimentalAnimationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            installSplashScreen()
            splashScreen.setOnExitAnimationListener { splashScreenView ->
                val slideUp = ObjectAnimator.ofFloat(
                    splashScreenView,
                    View.TRANSLATION_Y,
                    0f,
                    -splashScreenView.height.toFloat()
                )
                slideUp.interpolator = AnticipateInterpolator()
                slideUp.duration = 200L
                slideUp.doOnEnd { splashScreenView.remove() }
                slideUp.start()
            }
        }
        setContent {
            SwapTheme {
                Surface(
                    color = SwapTheme.colors.primaryBackgroundColor,
                    modifier = Modifier.fillMaxSize()
                ) {
                    val navController = rememberNavController()
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val scaffoldState = rememberScaffoldState()

                    StandardScaffold(
                        navController = navController,
                        showBottomBar = shouldShowBottomBar(navBackStackEntry),
                        state = scaffoldState,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Navigation(navController, scaffoldState)
                    }
                }
            }
        }
    }

    private fun shouldShowBottomBar(backStackEntry: NavBackStackEntry?): Boolean {
        val doesRouteMatch = backStackEntry?.destination?.route in listOf(
            Screen.HomeScreen.route,
            Screen.FavoritesScreen.route,
            Screen.DraftsScreen.route,
            Screen.ChatsListScreen.route,
            Screen.ProfileScreen.route
        )
        val isOwnProfile =
            backStackEntry?.destination?.route == "${Screen.ProfileScreen.route}?userId={userId}" &&
                    backStackEntry.arguments?.getString("userId") == null
        return doesRouteMatch || isOwnProfile
    }
}

@Composable
fun TopBar(
    navController: NavController,
    topBarState: MutableState<Boolean>,
    mode: MutableState<Boolean>
) {
    /*val navBackStackEntry = navController.currentBackStackEntryAsState()
    val title: String = when (navBackStackEntry.value?.destination?.route ?: "home") {
        "home" -> stringResource(R.string.bottom_menu_home)
        "favorite" -> stringResource(R.string.bottom_menu_favorite)
        "drafts" -> stringResource(R.string.bottom_menu_adverts)
        "chats" -> stringResource(R.string.bottom_menu_chats)
        "profile" -> stringResource(R.string.bottom_menu_profile)
        "logIn" -> stringResource(R.string.log_in_title_3)
        "signIn" -> stringResource(id = R.string.register_title)
        "new_advert" -> stringResource(R.string.new_advert)
        else -> ""
    }
    AnimatedVisibility(
        visible = topBarState.value,
        enter = slideInVertically(initialOffsetY = { -it }),
        exit = slideOutVertically(targetOffsetY = { -it }),
        content = {
            when (navBackStackEntry.value?.destination?.route) {
                "new_advert", "signIn", "logIn","camera" -> {
                    TopAppBar(
                        elevation = 7.dp,
                        navigationIcon = {
                            IconButton(onClick = { navController.navigateUp() }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_backarrow),
                                    contentDescription = "Back button",
                                    modifier = Modifier.size(40.dp),
                                    tint = if (isSystemInDarkTheme()) {
                                        Corn
                                    } else {
                                        Deep_dark_blue
                                    }
                                )
                            }
                        },
                        title = {
                            Text(
                                text = title,
                                color = if (isSystemInDarkTheme()) {
                                    Corn
                                } else {
                                    Deep_dark_blue
                                },
                                style = MaterialTheme.typography.h1
                            )
                        },
                    )
                }
                "home" -> {
                    TopAppBar(
                        elevation = 7.dp,
                        title = {
                            Text(
                                text = title,
                                color = if (isSystemInDarkTheme()) {
                                    Corn
                                } else {
                                    Deep_dark_blue
                                },
                                style = MaterialTheme.typography.h1
                            )
                        },
                        actions = {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_owl_search),
                                    contentDescription = "Filter and search",
                                    modifier = Modifier
                                        .size(40.dp)
                                        .clickable(
                                            enabled = true,
                                            onClick = {
                                                mode.value = !mode.value
                                            }
                                        ),
                                    tint = Color.Unspecified
                                )
                                Spacer(modifier = Modifier.requiredWidth(12.dp))
                        },
                    )
                }
                else -> {
                    TopAppBar(
                        elevation = 7.dp,
                        title = {
                            Text(
                                text = title,
                                color = if (isSystemInDarkTheme()) {
                                    Corn
                                } else {
                                    Deep_dark_blue
                                },
                                style = MaterialTheme.typography.h1
                            )
                        },
                    )
                }
            }
        }
    )*/
}

