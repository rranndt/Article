package dev.rranndt.article.data.local

import dev.rranndt.article.data.local.database.NewsDao
import dev.rranndt.article.data.local.mapper.toArticleEntity
import dev.rranndt.article.domain.model.Article
import javax.inject.Inject

/**
 * Created by Android Studio Giraffe
 * User: rranndt
 * Date: Mon, September 11
 */
class LocalDataSource @Inject constructor(private val newsDao: NewsDao) {
    suspend fun insertArticle(article: Article) =
        newsDao.insertArticle(article = article.toArticleEntity())

    suspend fun deleteArticle(article: Article) =
        newsDao.deleteArticle(article = article.toArticleEntity())

    suspend fun getArticle(url: String) = newsDao.getArticle(url = url)

    fun getArticles() = newsDao.getArticles()

    suspend fun clearArticle() = newsDao.clearArticle()

    suspend fun updateBookmarkArticle(article: Article, isBookmark: Boolean) {
        article.isBookmark = isBookmark
        newsDao.updateBookmarkArticle(article.toArticleEntity())
    }
}