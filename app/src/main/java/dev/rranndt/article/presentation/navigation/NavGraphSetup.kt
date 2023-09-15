package dev.rranndt.article.presentation.navigation

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import dev.rranndt.article.R
import dev.rranndt.article.domain.model.Article
import dev.rranndt.article.presentation.bookmark.BookmarkScreen
import dev.rranndt.article.presentation.bookmark.BookmarkViewModel
import dev.rranndt.article.presentation.detail.DetailScreen
import dev.rranndt.article.presentation.detail.DetailViewModel
import dev.rranndt.article.presentation.home.HomeScreen
import dev.rranndt.article.presentation.home.HomeViewModel
import dev.rranndt.article.presentation.search.SearchScreen
import dev.rranndt.article.presentation.search.SearchViewModel
import dev.rranndt.article.presentation.setting.SettingScreen
import dev.rranndt.article.util.UiText

/**
 * Created by Android Studio Giraffe
 * User: rranndt
 * Date: Sun, September 10
 */
@Composable
fun NavGraphSetup(navController: NavHostController) {
    Scaffold(
        bottomBar = {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentState = navBackStackEntry?.destination?.route

            if (currentState != Screen.DetailScreen.route) {
                if (currentState != null) {
                    navBackStackEntry?.let {
                        BottomBar(
                            navController = navController,
                            navBackStackEntry = it,
                            currentRoute = currentState,
                        )
                    }
                }
            }
        },
        contentWindowInsets = WindowInsets(0.dp)
    ) { paddingValues ->
        NavHost(
            modifier = Modifier.padding(paddingValues),
            navController = navController,
            startDestination = Screen.HomeScreen.route
        ) {
            homeRoute(navController = navController)
            searchRoute(navController = navController)
            bookmarkRoute(navController = navController)
            settingRoute()
            detailRoute(navController = navController)
        }
    }
}

fun NavGraphBuilder.homeRoute(navController: NavController) {
    composable(route = Screen.HomeScreen.route) {
        val viewModel = hiltViewModel<HomeViewModel>()
        val state by viewModel.state.collectAsStateWithLifecycle()
        val context = LocalContext.current

        HomeScreen(
            state = state,
            onEvent = viewModel::onEvent,
            onReadFullNewsButtonClick = { article ->
                navController
                    .currentBackStackEntry?.savedStateHandle?.set(
                        key = UiText.Resource(R.string.args).asString(context),
                        value = article
                    )
                navController.navigate(Screen.DetailScreen.route)
            },
        )
    }
}

fun NavGraphBuilder.searchRoute(navController: NavController) {
    composable(route = Screen.SearchScreen.route) {
        val viewModel = hiltViewModel<SearchViewModel>()
        val state by viewModel.state.collectAsStateWithLifecycle()
        val context = LocalContext.current

        SearchScreen(
            state = state,
            onEvent = viewModel::onEvent,
            onReadFullNewsButtonClick = { article ->
                navController
                    .currentBackStackEntry?.savedStateHandle?.set(
                        key = UiText.Resource(R.string.args).asString(context),
                        value = article
                    )
                navController.navigate(Screen.DetailScreen.route)
            }
        )
    }
}

fun NavGraphBuilder.bookmarkRoute(navController: NavController) {
    composable(route = Screen.BookmarkScreen.route) {
        val viewModel = hiltViewModel<BookmarkViewModel>()
        val state by viewModel.state.collectAsStateWithLifecycle()
        val context = LocalContext.current

        BookmarkScreen(
            state = state,
            onEvent = viewModel::onEvent,
            onReadFullNewsButtonClick = { article ->
                navController
                    .currentBackStackEntry?.savedStateHandle?.set(
                        key = UiText.Resource(R.string.args).asString(context),
                        value = article
                    )
                navController.navigate(Screen.DetailScreen.route)
            }
        )
    }
}

fun NavGraphBuilder.settingRoute() {
    composable(route = Screen.SettingScreen.route) {
        SettingScreen()
    }
}

fun NavGraphBuilder.detailRoute(navController: NavController) {
    composable(route = Screen.DetailScreen.route) {
        val viewModel = hiltViewModel<DetailViewModel>()
        val state by viewModel.uiComponent.collectAsStateWithLifecycle()
        val context = LocalContext.current
        val result = navController
            .previousBackStackEntry?.savedStateHandle?.get<Article>(
                UiText.Resource(R.string.args).asString(context)
            )

        if (result != null) {
            DetailScreen(
                onEvent = viewModel::onEvent,
                onBackPress = { navController.navigateUp() },
                article = result,
                uiComponent = state,
            )
        }
    }
}