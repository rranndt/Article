package dev.rranndt.article.data.remote

import javax.inject.Inject

/**
 * Created by Android Studio Giraffe
 * User: rranndt
 * Date: Sat, September 09
 */
class RemoteDataSource @Inject constructor(private val newsApi: NewsApi) {
    suspend fun getHeadlineNews(category: String) = newsApi.getHeadlineNews(category = category)

    suspend fun searchNews(query: String) = newsApi.searchNews(query = query)
}