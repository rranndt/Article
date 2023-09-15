package dev.rranndt.article.presentation.navigation

import androidx.annotation.DrawableRes

/**
 * Created by Android Studio Giraffe
 * User: rranndt
 * Date: Sun, September 10
 */
data class NavigationItem(
    val title: String,
    @DrawableRes val icon: Int,
    @DrawableRes val iconFocused: Int,
    val screen: Screen
)
