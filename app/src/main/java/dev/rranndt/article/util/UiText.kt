package dev.rranndt.article.util

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

/**
 * Created by Android Studio Giraffe
 * User: rranndt
 * Date: Wed, September 13
 */
sealed class UiText {
    data class Dynamic(val value: String) : UiText()
    class Resource(
        @StringRes val id: Int,
        vararg val args: Any
    ) : UiText()

    @Composable
    fun asString(): String {
        return when (this) {
            is Dynamic -> value
            is Resource -> stringResource(
                id = id,
                formatArgs = args
            )
        }
    }

    fun asString(context: Context): String {
        return when (this) {
            is Dynamic -> value
            is Resource -> context.getString(id, *args)
        }
    }
}
