package dev.rranndt.article.data.remote.dto

import com.google.gson.annotations.SerializedName

/**
 * Created by Android Studio Giraffe
 * User: rranndt
 * Date: Sat, September 09
 */
data class NewsResponseDto(
    @SerializedName("status")
    val status: String,

    @SerializedName("totalResults")
    val totalResults: Int,

    @SerializedName("articles")
    val articles: List<ArticleDto>
)
