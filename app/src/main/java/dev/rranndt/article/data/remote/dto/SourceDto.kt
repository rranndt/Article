package dev.rranndt.article.data.remote.dto

import com.google.gson.annotations.SerializedName

/**
 * Created by Android Studio Giraffe
 * User: rranndt
 * Date: Sat, September 09
 */
data class SourceDto(
    @SerializedName("id")
    val id: String?,

    @SerializedName("name")
    val name: String?,
)
