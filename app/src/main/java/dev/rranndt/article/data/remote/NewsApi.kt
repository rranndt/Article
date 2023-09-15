package dev.rranndt.article.data.remote

import dev.rranndt.article.data.remote.dto.NewsResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Android Studio Giraffe
 * User: rranndt
 * Date: Sat, September 09
 */
interface NewsApi {
    @GET("top-headlines")
    suspend fun getHeadlineNews(
        @Query("country") country: String = "us",
        @Query("category") category: String,
    ): NewsResponseDto

    @GET("everything")
    suspend fun searchNews(
        @Query("q") query: String,
        @Query("language") language: String = "en",
    ): NewsResponseDto
}