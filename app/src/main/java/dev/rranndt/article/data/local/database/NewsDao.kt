package dev.rranndt.article.data.local.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import dev.rranndt.article.data.local.entity.ArticleEntity
import kotlinx.coroutines.flow.Flow

/**
 * Created by Android Studio Giraffe
 * User: rranndt
 * Date: Sun, September 10
 */
@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(article: ArticleEntity)

    @Delete
    suspend fun deleteArticle(article: ArticleEntity)

    @Query("SELECT * FROM article_entity WHERE url = :url")
    suspend fun getArticle(url: String): ArticleEntity?

    @Query("SELECT * FROM article_entity")
    fun getArticles(): Flow<List<ArticleEntity>>

    @Query("DELETE FROM article_entity")
    suspend fun clearArticle()

    @Update
    suspend fun updateBookmarkArticle(article: ArticleEntity)
}