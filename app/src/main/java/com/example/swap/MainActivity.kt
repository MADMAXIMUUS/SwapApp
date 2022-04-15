package com.example.swap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.swap.chatscreen.ChatsScreen
import com.example.swap.favoritescreen.FavoriteScreen
import com.example.swap.homescreen.HomeScreen
import com.example.swap.models.BillModel
import com.example.swap.models.BottomNavItem
import com.example.swap.newbillscreen.NewBillScreen
import com.example.swap.profilescreen.ProfileScreen
import com.example.swap.ui.theme.Light_brown
import com.example.swap.ui.theme.SwapTheme
import com.example.swap.ui.theme.Yellow


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SwapTheme {
                LoadMainUi()
            }
        }
    }
}

@Preview(
    name = "Main Activity",
    showSystemUi = true,
    showBackground = true,
)
@Composable
fun LoadMainUi() {
    val navController = rememberNavController()
    val mode = remember {
        mutableStateOf(false)
    }
    val currentScreen = remember { mutableStateOf("Home") }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = currentScreen.value,
                        style = MaterialTheme.typography.h1
                    )
                },
                elevation = 7.dp,
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
        },
        content = {
            Navigation(navController = navController, mode = mode.value)
        },
        bottomBar = {
            BottomNavigationBar(
                items = listOf(
                    BottomNavItem(
                        name = stringResource(R.string.bottom_menu_home),
                        route = "Home",
                        icon = painterResource(id = R.drawable.ic_bottom_main)
                    ),
                    BottomNavItem(
                        name = stringResource(R.string.bottom_menu_favorite),
                        route = "Favorite",
                        icon = painterResource(id = R.drawable.ic_bottom_favorite)
                    ),
                    BottomNavItem(
                        name = stringResource(R.string.bottom_menu_bills),
                        route = "Bills",
                        icon = painterResource(id = R.drawable.ic_bottom_add)
                    ),
                    BottomNavItem(
                        name = stringResource(R.string.bottom_menu_chats),
                        route = "Chats",
                        icon = painterResource(id = R.drawable.ic_bottom_chats),
                        badgeCount = 10
                    ),
                    BottomNavItem(
                        name = stringResource(R.string.bottom_menu_profile),
                        route = "Profile",
                        icon = painterResource(id = R.drawable.ic_bottom_profile)
                    )
                ),
                navController = navController,
                onItemClick = { item ->
                    navController.navigate(item.route)
                    if ((currentScreen.value == "Home" || currentScreen.value == "Главная")
                        && navController.currentDestination?.route == "Home"
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
        startDestination = "Home",
        modifier = Modifier
            .padding(0.dp, 0.dp, 0.dp, 55.dp)
    ) {
        composable("Home") {
            HomeScreen(
                mode,
                listOf(
                    BillModel("00", "Подушка", "Хорошая подушка надо брать"),
                    BillModel("00", "Подушка", "Хорошая подушка надо брать"),
                    BillModel("00", "Подушка", "Хорошая подушка надо брать"),
                    BillModel("00", "Подушка", "Хорошая подушка надо брать"),
                    BillModel("00", "Подушка", "Хорошая подушка надо брать"),
                    BillModel("00", "Подушка", "Хорошая подушка надо брать"),
                    BillModel("00", "Подушка", "Хорошая подушка надо брать"),
                    BillModel("00", "Подушка", "Хорошая подушка надо брать"),
                    BillModel("00", "Подушка", "Хорошая подушка надо брать"),
                    BillModel("00", "Подушка", "Хорошая подушка надо брать"),
                    BillModel("00", "Подушка", "Хорошая подушка надо брать"),
                    BillModel("00", "Подушка", "Хорошая подушка надо брать"),
                    BillModel("00", "Подушка", "Хорошая подушка надо брать"),
                    BillModel("00", "Подушка", "Хорошая подушка надо брать"),
                    BillModel("00", "Подушка", "Хорошая подушка надо брать")
                )
            )
        }
        composable("Favorite") {
            FavoriteScreen(listOf())
        }
        composable("Bills") {
            NewBillScreen(listOf())
        }
        composable("Chats") {
            ChatsScreen()
        }
        composable("Profile") {
            ProfileScreen()
        }
    }
}

@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    navController: NavHostController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit
) {
    val backStackEntry = navController.currentBackStackEntryAsState()
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
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            )
        }
    }
}
