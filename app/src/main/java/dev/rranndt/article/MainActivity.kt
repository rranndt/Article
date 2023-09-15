package dev.rranndt.article

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.rranndt.article.presentation.navigation.NavGraphSetup
import dev.rranndt.article.presentation.theme.ArticleTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        enableEdgeToEdge()

        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            ArticleTheme(dynamicColor = false) {
                Box(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background)
                        .windowInsetsPadding(insets = WindowInsets.statusBars)
                ) {
                    val navController = rememberNavController()
                    NavGraphSetup(navController = navController)
                }
            }
        }
    }
}