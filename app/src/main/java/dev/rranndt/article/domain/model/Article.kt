package dev.rranndt.article.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

/**
 * Created by Android Studio Giraffe
 * User: rranndt
 * Date: Sat, September 09
 */
@Parcelize
data class Article(
    val source: @RawValue Source,
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String?,
    val content: String?,
    var isBookmark: Boolean
) : Parcelable
