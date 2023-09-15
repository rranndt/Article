package dev.rranndt.article.util

/**
 * Created by Android Studio Giraffe
 * User: rranndt
 * Date: Sat, September 09
 */
sealed class DataState<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T?) : DataState<T>(data = data)
    class Error<T>(message: String?) : DataState<T>(message = message)
}
