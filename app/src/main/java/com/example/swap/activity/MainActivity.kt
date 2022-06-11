package com.example.swap.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.swap.R
import com.example.swap.chatscreen.ChatsScreen
import com.example.swap.favoritescreen.FavoriteScreen
import com.example.swap.homescreen.HomeScreen
import com.example.swap.models.BottomNavItem
import com.example.swap.newbillscreen.DraftsScreen
import com.example.swap.newbillscreen.NewBillScreen
import com.example.swap.profilescreen.LogInScreen
import com.example.swap.profilescreen.ProfileScreen
import com.example.swap.profilescreen.SignInScreen
import com.example.swap.ui.theme.Deep_dark_blue
import com.example.swap.ui.theme.Light_brown
import com.example.swap.ui.theme.SwapTheme
import com.example.swap.ui.theme.Yellow
import com.example.swap.utilities.HideKeyboard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.isLoading.value
            }
        }
        setContent {
            HideKeyboard()
            SwapTheme {
                LoadMainUi()
            }
        }
    }
}

@Composable
fun LoadMainUi() {
    val navController = rememberNavController()
    val mode = remember {
        mutableStateOf(false)
    }
    val bottomBarState = rememberSaveable { (mutableStateOf(true)) }
    val topBarState = rememberSaveable { (mutableStateOf(true)) }
    val currentScreen = remember { mutableStateOf("Home") }
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    when (navBackStackEntry.value?.destination?.route) {
        "home" -> {
            bottomBarState.value = true
            topBarState.value = true
        }
        "favorites" -> {
            bottomBarState.value = true
            topBarState.value = true
        }
        "chats" -> {
            bottomBarState.value = true
            topBarState.value = true
        }
        "bills" -> {
            bottomBarState.value = true
            topBarState.value = true
        }
        "profile" -> {
            bottomBarState.value = true
            topBarState.value = true
        }
        "new_bill" -> {
            bottomBarState.value = false
            topBarState.value = true
        }
        "logIn"->{
            bottomBarState.value=false
            topBarState.value=true
        }
        "signIn"->{
            bottomBarState.value=false
            topBarState.value=true
        }
    }
    Scaffold(
        topBar = {
            TopBar(
                navController = navController,
                topBarState = topBarState
            )
        },
        content = {
            Navigation(navController = navController, mode = mode.value)
        },
        bottomBar = {
            BottomNavigationBar(
                bottomBarState = bottomBarState,
                items = listOf(
                    BottomNavItem(
                        name = stringResource(R.string.bottom_menu_home),
                        route = "home",
                        icon = painterResource(id = R.drawable.ic_bottom_main)
                    ),
                    BottomNavItem(
                        name = stringResource(R.string.bottom_menu_favorite),
                        route = "favorite",
                        icon = painterResource(id = R.drawable.ic_bottom_favorite)
                    ),
                    BottomNavItem(
                        name = stringResource(R.string.bottom_menu_bills),
                        route = "bills",
                        icon = painterResource(id = R.drawable.ic_bottom_add)
                    ),
                    BottomNavItem(
                        name = stringResource(R.string.bottom_menu_chats),
                        route = "chats",
                        icon = painterResource(id = R.drawable.ic_bottom_chats),
                        badgeCount = 10
                    ),
                    BottomNavItem(
                        name = stringResource(R.string.bottom_menu_profile),
                        route = "profile",
                        icon = painterResource(id = R.drawable.ic_bottom_profile)
                    )
                ),
                navController = navController,
                onItemClick = { item ->
                    navController.navigate(item.route)
                    if ((currentScreen.value == "Home" || currentScreen.value == "Главная")
                        && navController.currentDestination?.route == "home"
                    )
                        mode.value = !mode.value
                    currentScreen.value = item.name
                }
            )
        }
    )
}

@Composable
fun Navigation(
    mode: Boolean,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = "home",
        modifier = Modifier
            .fillMaxSize()
    ) {
        composable("home") {
            HomeScreen(
                mode, listOf()
            )
        }
        composable("favorite") {
            FavoriteScreen(listOf())
        }
        composable("bills") {
            DraftsScreen(listOf(), navController)
        }
        composable("chats") {
            ChatsScreen(
                listOf(),
                navController
            )
        }
        composable("profile") {
            ProfileScreen(navController)
        }
        composable("new_bill") {
            NewBillScreen(navController)
        }
        composable("signIn") {
            SignInScreen(navController)
        }
        composable("logIn") {
            LogInScreen(navController)
        }
        composable(
            "chat/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.LongType
                }
            )
        ) {
            val id = remember {
                it.arguments?.getLong("id")
            }

        }
    }
}

@Composable
fun TopBar(navController: NavController, topBarState: MutableState<Boolean>) {
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val title: String = when (navBackStackEntry.value?.destination?.route ?: "home") {
        "home" -> stringResource(R.string.bottom_menu_home)
        "favorite" -> stringResource(R.string.bottom_menu_favorite)
        "bills" -> stringResource(R.string.bottom_menu_bills)
        "chats" -> stringResource(R.string.bottom_menu_chats)
        "profile" -> stringResource(R.string.bottom_menu_profile)
        "logIn" -> stringResource(R.string.log_in_title_3)
        "signIn" -> stringResource(id = R.string.register_title)
        "new_bill" -> stringResource(R.string.new_bill)
        else -> ""
    }
    AnimatedVisibility(
        visible = topBarState.value,
        enter = slideInVertically(initialOffsetY = { -it }),
        exit = slideOutVertically(targetOffsetY = { -it }),
        content = {
            when (navBackStackEntry.value?.destination?.route) {
                "new_bill", "signIn", "logIn"-> {
                    TopAppBar(
                        elevation = 7.dp,
                        navigationIcon = {
                            IconButton(onClick = { navController.navigateUp() }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_backarrow),
                                    contentDescription = "Back button",
                                    modifier = Modifier.size(40.dp),
                                    tint = if (isSystemInDarkTheme()) {
                                        Yellow
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
                                    Yellow
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
                                    Yellow
                                } else {
                                    Deep_dark_blue
                                },
                                style = MaterialTheme.typography.h1
                            )
                        },
                        actions = {
                            Row {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_owl_search),
                                    contentDescription = "Filter and search",
                                    modifier = Modifier.size(40.dp),
                                    tint = Color.Unspecified
                                )
                                Spacer(modifier = Modifier.requiredWidth(12.dp))
                            }
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
                                    Yellow
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
    )
}

@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    navController: NavHostController,
    modifier: Modifier = Modifier,
    bottomBarState: MutableState<Boolean>,
    onItemClick: (BottomNavItem) -> Unit
) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    AnimatedVisibility(
        visible = bottomBarState.value,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it }),
        content = {
            BottomNavigation(
                modifier = modifier,
                backgroundColor = MaterialTheme.colors.surface,
                elevation = 7.dp
            ) {
                items.forEach { item ->
                    val selected = item.route == backStackEntry.value?.destination?.route
                    BottomNavigationItem(
                        selected = selected,
                        onClick = { onItemClick(item) },
                        selectedContentColor = Light_brown,
                        unselectedContentColor = Yellow,
                        alwaysShowLabel = true,
                        icon = {
                            Column(horizontalAlignment = CenterHorizontally) {
                                if (item.badgeCount > 0)
                                    if (item.badgeCount > 99) {
                                        BadgedBox(badge = {
                                            Badge(
                                                backgroundColor = MaterialTheme.colors.error,
                                                modifier = Modifier.offset((-5).dp, 5.dp)
                                            ) {
                                                Text(
                                                    text = "99+",
                                                    modifier = Modifier.padding(1.5.dp),
                                                    color = Color.White
                                                )
                                            }
                                        }) {
                                            Icon(
                                                painter = item.icon,
                                                contentDescription = item.name,
                                                modifier = Modifier.size(25.dp)
                                            )
                                        }
                                    } else {
                                        BadgedBox(badge = {
                                            Badge(
                                                backgroundColor = MaterialTheme.colors.error,
                                                modifier = Modifier.offset((-5).dp, 5.dp)
                                            ) {
                                                Text(
                                                    text = item.badgeCount.toString(),
                                                    modifier = Modifier.padding(1.5.dp),
                                                    color = Color.White
                                                )
                                            }
                                        }) {
                                            Icon(
                                                painter = item.icon,
                                                contentDescription = item.name,
                                                modifier = Modifier.size(25.dp)
                                            )
                                        }
                                    }
                                else {
                                    Icon(
                                        painter = item.icon,
                                        contentDescription = item.name,
                                        modifier = Modifier.size(25.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = item.name,
                                    style = MaterialTheme.typography.subtitle1
                                )
                            }
                        }
                    )
                }
            }
        }
    )
}

