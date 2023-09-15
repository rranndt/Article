package dev.rranndt.article.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.rranndt.article.R
import dev.rranndt.article.data.local.NewsTypeConverter
import dev.rranndt.article.data.local.database.NewsDao
import dev.rranndt.article.data.local.database.NewsDatabase
import dev.rranndt.article.util.UiText
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

/**
 * Created by Android Studio Giraffe
 * User: rranndt
 * Date: Sun, September 10
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideNewsDatabase(application: Application): NewsDatabase {
        val passPhrase: ByteArray = SQLiteDatabase.getBytes(
            UiText.Resource(R.string.database_name).asString(application).toCharArray()
        )
        val factory = SupportFactory(passPhrase)
        return Room.databaseBuilder(
            context = application,
            klass = NewsDatabase::class.java,
            name = UiText.Resource(R.string.database_name).asString(application)
        ).addTypeConverter(NewsTypeConverter())
            .fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(newsDatabase: NewsDatabase): NewsDao = newsDatabase.newsDao
}