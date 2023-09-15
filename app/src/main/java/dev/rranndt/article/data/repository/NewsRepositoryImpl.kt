package dev.rranndt.article.data.repository

import android.app.Application
import dev.rranndt.article.R
import dev.rranndt.article.data.local.LocalDataSource
import dev.rranndt.article.data.local.mapper.toArticle
import dev.rranndt.article.data.remote.RemoteDataSource
import dev.rranndt.article.data.remote.mapper.toArticle
import dev.rranndt.article.domain.model.Article
import dev.rranndt.article.domain.repository.NewsRepository
import dev.rranndt.article.util.DataState
import dev.rranndt.article.util.UiText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import okio.IOException
import javax.inject.Inject

/**
 * Created by Android Studio Giraffe
 * User: rranndt
 * Date: Sat, September 09
 */
class NewsRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val application: Application
) : NewsRepository {
    override suspend fun getHeadlineNews(category: String): Flow<DataState<List<Article>>> {
        return flow {
            try {
                val response = remoteDataSource.getHeadlineNews(category = category)
                val result = response.articles.map { it.toArticle() }
                emit(DataState.Success(result))
            } catch (e: IOException) {
                emit(
                    DataState.Error(
                        message = UiText.Resource(R.string.io_exception)
                            .asString(application)
                    )
                )
            } catch (e: Exception) {
                emit(
                    DataState.Error(
                        message = UiText.Resource(R.string.exception, e.message ?: "")
                            .asString(application)
                    )
                )
            }
        }
    }

    override suspend fun searchNews(query: String): Flow<DataState<List<Article>>> {
        return flow {
            try {
                val response = remoteDataSource.searchNews(query = query)
                val result = response.articles.map { it.toArticle() }
                emit(DataState.Success(result))
            } catch (e: IOException) {
                emit(
                    DataState.Error(
                        message = UiText.Resource(R.string.io_exception)
                            .asString(application)
                    )
                )
            } catch (e: Exception) {
                emit(
                    DataState.Error(
                        message = UiText.Resource(R.string.exception, e.message ?: "")
                            .asString(application)
                    )
                )
            }
        }
    }

    override suspend fun insertArticle(article: Article) {
        localDataSource.insertArticle(article)
    }

    override suspend fun deleteArticle(article: Article) {
        localDataSource.deleteArticle(article)
    }

    override suspend fun getArticle(url: String): Article? {
        return localDataSource.getArticle(url)?.toArticle()
    }

    override fun getArticles(): Flow<List<Article>> {
        return localDataSource.getArticles().map { toArticle(it) }
    }

    override suspend fun clearArticle() {
        localDataSource.clearArticle()
    }

    override suspend fun updateBookmarkArticle(article: Article, isBookmark: Boolean) {
        localDataSource.updateBookmarkArticle(article, isBookmark)
    }
}