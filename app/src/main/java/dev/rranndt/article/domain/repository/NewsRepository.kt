package dev.rranndt.article.domain.repository

import dev.rranndt.article.domain.model.Article
import dev.rranndt.article.util.DataState
import kotlinx.coroutines.flow.Flow

/**
 * Created by Android Studio Giraffe
 * User: rranndt
 * Date: Sat, September 09
 */
interface NewsRepository {
    suspend fun getHeadlineNews(category: String): Flow<DataState<List<Article>>>

    suspend fun searchNews(query: String): Flow<DataState<List<Article>>>

    suspend fun insertArticle(article: Article)

    suspend fun deleteArticle(article: Article)

    suspend fun getArticle(url: String): Article?

    fun getArticles(): Flow<List<Article>>

    suspend fun clearArticle()

    suspend fun updateBookmarkArticle(article: Article, isBookmark: Boolean)
}