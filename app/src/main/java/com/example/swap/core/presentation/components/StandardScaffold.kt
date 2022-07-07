package com.example.swap.core.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.swap.R
import com.example.swap.core.domain.models.BottomNavItem
import com.example.swap.core.presentation.ui.theme.Red
import com.example.swap.core.presentation.ui.theme.SwapTheme
import com.example.swap.core.util.Screen

@Composable
fun StandardScaffold(
    navController: NavController,
    modifier: Modifier = Modifier,
    showBottomBar: Boolean = true,
    state: ScaffoldState,
    bottomNavItems: List<BottomNavItem> = listOf(
        BottomNavItem(
            route = Screen.HomeScreen.route,
            title = stringResource(id = R.string.bottom_menu_home),
            icon = ImageVector.vectorResource(id = R.drawable.ic_bottom_home),
            contentDescription = "Home"
        ),
        BottomNavItem(
            route = Screen.FavoritesScreen.route,
            title = stringResource(id = R.string.bottom_menu_favorite),
            icon = ImageVector.vectorResource(id = R.drawable.ic_bottom_favorite),
            contentDescription = "Favorite"
        ),
        BottomNavItem(
            route = Screen.DraftsScreen.route,
            title = stringResource(id = R.string.bottom_menu_drafts),
            icon = ImageVector.vectorResource(id = R.drawable.ic_bottom_drafts),
            contentDescription = "Draft"
        ),
        BottomNavItem(
            route = Screen.ChatScreen.route,
            title = stringResource(id = R.string.bottom_menu_chats),
            icon = ImageVector.vectorResource(id = R.drawable.ic_bottom_chats),
            contentDescription = "Chat"
        ),
        BottomNavItem(
            route = Screen.ProfileScreen.route,
            title = stringResource(id = R.string.bottom_menu_profile),
            icon = ImageVector.vectorResource(id = R.drawable.ic_bottom_profile),
            contentDescription = "Profile"
        ),
    ),
    content: @Composable () -> Unit
) {
    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                BottomAppBar(
                    modifier = Modifier.fillMaxWidth(),
                    backgroundColor = SwapTheme.colors.barColor,
                    cutoutShape = CircleShape,
                    elevation = 7.dp
                ) {
                    BottomNavigation(
                        modifier = Modifier
                            .background(color = SwapTheme.colors.barColor)
                    ) {
                        bottomNavItems.forEachIndexed { i, bottomNavItem ->
                            StandardBottomNavItem(
                                icon = bottomNavItem.icon,
                                title = bottomNavItem.title,
                                contentDescription = bottomNavItem.contentDescription,
                                selected = navController.currentDestination?.route?.startsWith(
                                    bottomNavItem.route
                                ) == true,
                                alertCount = bottomNavItem.alertCount
                            ) {
                                if (navController.currentDestination?.route != bottomNavItem.route) {
                                    navController.navigate(bottomNavItem.route)
                                }
                            }
                        }
                    }
                }
            }
        },
        scaffoldState = state,
        modifier = modifier
    ) {
        content()
    }
}