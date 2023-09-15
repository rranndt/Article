package dev.rranndt.article.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.rranndt.article.domain.repository.NewsRepository
import dev.rranndt.article.domain.usecase.ClearArticle
import dev.rranndt.article.domain.usecase.DeleteArticle
import dev.rranndt.article.domain.usecase.GetArticle
import dev.rranndt.article.domain.usecase.GetArticles
import dev.rranndt.article.domain.usecase.GetHeadlineNews
import dev.rranndt.article.domain.usecase.InsertArticle
import dev.rranndt.article.domain.usecase.NewsUseCases
import dev.rranndt.article.domain.usecase.SearchNews
import dev.rranndt.article.domain.usecase.UpdateBookmarkArticle
import javax.inject.Singleton

/**
 * Created by Android Studio Giraffe
 * User: rranndt
 * Date: Sat, September 09
 */
@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    @Singleton
    fun provideNewsUseCases(newsRepository: NewsRepository): NewsUseCases {
        return NewsUseCases(
            getHeadlineNews = GetHeadlineNews(newsRepository),
            searchNews = SearchNews(newsRepository),
            insertArticle = InsertArticle(newsRepository),
            deleteArticle = DeleteArticle(newsRepository),
            getArticle = GetArticle(newsRepository),
            getArticles = GetArticles(newsRepository),
            clearArticle = ClearArticle(newsRepository),
            updateBookmarkArticle = UpdateBookmarkArticle(newsRepository)
        )
    }
}