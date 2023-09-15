package dev.rranndt.article.presentation.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import dev.rranndt.article.R

/**
 * Created by Android Studio Giraffe
 * User: rranndt
 * Date: Sun, September 10
 */
@Composable
fun BottomBar(
    navController: NavController,
    navBackStackEntry: NavBackStackEntry,
    currentRoute: String,
) {
    NavigationBar {
        val navigationItem = listOf(
            NavigationItem(
                title = stringResource(id = R.string.news_title),
                icon = R.drawable.ic_news,
                iconFocused = R.drawable.ic_news_filled,
                screen = Screen.HomeScreen
            ),
            NavigationItem(
                title = stringResource(id = R.string.search_title),
                icon = R.drawable.ic_search,
                iconFocused = R.drawable.ic_search_filled,
                screen = Screen.SearchScreen
            ),
            NavigationItem(
                title = stringResource(id = R.string.bookmark_title),
                icon = R.drawable.ic_bookmarks,
                iconFocused = R.drawable.ic_bookmarks_filled,
                screen = Screen.BookmarkScreen
            ),
            NavigationItem(
                title = stringResource(id = R.string.setting_title),
                icon = R.drawable.ic_setting,
                iconFocused = R.drawable.ic_setting_filled,
                screen = Screen.SettingScreen
            ),
        )
        NavigationBar {
            navigationItem.map { item ->
                val selected = navBackStackEntry.destination.hierarchy.any {
                    it.route == item.screen.route
                }
                NavigationBarItem(
                    selected = currentRoute == item.screen.route,
                    icon = {
                        Icon(
                            painter = painterResource(id = if (selected) item.iconFocused else item.icon),
                            contentDescription = item.title
                        )
                    },
                    label = { Text(text = item.title) },
                    onClick = {
                        navController.navigate(item.screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    }
}