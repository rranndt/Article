package dev.rranndt.article.util

/**
 * Created by Android Studio Giraffe
 * User: rranndt
 * Date: Wed, September 13
 */
sealed class UiComponent {
    data class SnackBar(val message: String) : UiComponent()
}
