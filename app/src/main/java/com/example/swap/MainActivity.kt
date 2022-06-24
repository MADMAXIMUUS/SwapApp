package com.example.swap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.clickable
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.swap.objects.BottomNavItem
import com.example.swap.presentation.advertscreen.AdvertScreen
import com.example.swap.presentation.camerascreen.CameraScreen
import com.example.swap.presentation.chatscreen.ChatScreen
import com.example.swap.presentation.chatscreen.ChatsScreen
import com.example.swap.presentation.draftscreen.draft.DraftsScreen
import com.example.swap.presentation.draftscreen.new_advert.AdvertViewModel
import com.example.swap.presentation.draftscreen.new_advert.NewAdvertScreen
import com.example.swap.presentation.favoritescreen.FavoriteScreen
import com.example.swap.presentation.homescreen.HomeScreen
import com.example.swap.presentation.profilescreen.LogInScreen
import com.example.swap.presentation.profilescreen.ProfileScreen
import com.example.swap.presentation.profilescreen.SignInScreen
import com.example.swap.presentation.profilescreen.viewmodels.AuthenticationViewModel
import com.example.swap.presentation.profilescreen.viewmodels.UserViewModel
import com.example.swap.ui.theme.*
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
    val authViewModel: AuthenticationViewModel = hiltViewModel()
    val advertViewModel: AdvertViewModel = hiltViewModel()
    val userViewModel: UserViewModel = hiltViewModel()
    val mode = remember {
        mutableStateOf(false)
    }
    val gridMode = remember {
        mutableStateOf(false)
    }
    val bottomBarState = rememberSaveable { (mutableStateOf(true)) }
    val topBarState = rememberSaveable { (mutableStateOf(true)) }
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
        "drafts" -> {
            bottomBarState.value = true
            topBarState.value = true
        }
        "profile" -> {
            bottomBarState.value = true
            topBarState.value = true
        }
        "new_advert" -> {
            bottomBarState.value = false
            topBarState.value = true
        }
        "logIn" -> {
            bottomBarState.value = false
            topBarState.value = true
        }
        "signIn" -> {
            bottomBarState.value = false
            topBarState.value = true
        }
        "advert/{id}" -> {
            bottomBarState.value = false
            topBarState.value = true
        }
        "camera" -> {
            bottomBarState.value = false
            topBarState.value = true
        }
    }
    Scaffold(
        topBar = {
            TopBar(
                navController = navController,
                topBarState = topBarState,
                mode = mode
            )
        },
        content = {
            Navigation(
                navController = navController,
                mode = gridMode.value,
                authViewModel = authViewModel,
                advertViewModel = advertViewModel,
                userViewModel = userViewModel
            )
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
                        name = stringResource(R.string.bottom_menu_adverts),
                        route = "drafts",
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
                }
            )
        }
    )
}

@Composable
fun Navigation(
    mode: Boolean,
    navController: NavHostController,
    authViewModel: AuthenticationViewModel,
    advertViewModel: AdvertViewModel,
    userViewModel: UserViewModel
) {
    NavHost(
        navController = navController,
        startDestination = "home",
        modifier = Modifier
            .fillMaxSize()
    ) {
        composable("home") {
            HomeScreen(
                mode, navController, advertViewModel
            )
        }
        composable("favorite") {
            FavoriteScreen(navController)
        }
        composable("drafts") {
            DraftsScreen(navController, authViewModel)
        }
        composable("chats") {
            ChatsScreen(navController)
        }
        composable("profile") {
            ProfileScreen(navController, authViewModel, userViewModel)
        }
        composable("new_advert") {
            NewAdvertScreen(navController, advertViewModel)
        }
        composable("signIn") {
            SignInScreen(navController, authViewModel)
        }
        composable("logIn") {
            LogInScreen(navController, authViewModel)
        }
        composable("camera") {
            CameraScreen(navController)
        }
        composable(
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
        }
    }
}

@Composable
fun TopBar(
    navController: NavController,
    topBarState: MutableState<Boolean>,
    mode: MutableState<Boolean>
) {
    val navBackStackEntry = navController.currentBackStackEntryAsState()
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
                        selectedContentColor = if (isSystemInDarkTheme()) {
                            Light_brown
                        } else {
                            Deep_dark_blue
                        },
                        unselectedContentColor = if (isSystemInDarkTheme()) {
                            Yellow
                        } else {
                            Color.Gray
                        },
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

