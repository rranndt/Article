package dev.rranndt.article.domain.usecase

import dev.rranndt.article.domain.model.Article
import dev.rranndt.article.domain.repository.NewsRepository
import dev.rranndt.article.util.DataState
import kotlinx.coroutines.flow.Flow

/**
 * Created by Android Studio Giraffe
 * User: rranndt
 * Date: Sat, September 09
 */
class GetHeadlineNews(private val newsRepository: NewsRepository) {
    suspend operator fun invoke(category: String): Flow<DataState<List<Article>>> {
        return newsRepository.getHeadlineNews(category = category)
    }
}