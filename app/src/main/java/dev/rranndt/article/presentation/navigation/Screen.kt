package dev.rranndt.article.presentation.navigation

/**
 * Created by Android Studio Giraffe
 * User: rranndt
 * Date: Sun, September 10
 */
sealed class Screen(val route: String) {
    data object HomeScreen : Screen("home_screen")
    data object SearchScreen : Screen("search_screen")
    data object BookmarkScreen : Screen("bookmarks_screen")
    data object SettingScreen : Screen("setting_screen")
    data object DetailScreen : Screen("detail_screen")
}
