package dev.rranndt.article.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.RawValue

/**
 * Created by Android Studio Giraffe
 * User: rranndt
 * Date: Sun, September 10
 */
@Entity(tableName = "article_entity")
data class ArticleEntity(
    @ColumnInfo(name = "source")
    val source: @RawValue SourceEntity,

    @ColumnInfo(name = "author")
    val author: String,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "urlToImage")
    val urlToImage: String,

    @ColumnInfo(name = "publishedAt")
    val publishedAt: String,

    @ColumnInfo(name = "content")
    val content: String,

    @ColumnInfo(name = "isBookmark")
    var isBookmark: Boolean,

    @PrimaryKey
    @ColumnInfo(name = "url")
    val url: String
)
