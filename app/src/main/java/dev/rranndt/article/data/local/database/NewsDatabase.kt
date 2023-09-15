package dev.rranndt.article.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.rranndt.article.data.local.NewsTypeConverter
import dev.rranndt.article.data.local.entity.ArticleEntity

/**
 * Created by Android Studio Giraffe
 * User: rranndt
 * Date: Sun, September 10
 */
@Database(
    entities = [ArticleEntity::class],
    version = 1
)
@TypeConverters(NewsTypeConverter::class)
abstract class NewsDatabase : RoomDatabase() {
    abstract val newsDao: NewsDao
}