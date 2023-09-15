package dev.rranndt.article.presentation.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.rranndt.article.R
import dev.rranndt.article.domain.model.Article
import dev.rranndt.article.domain.usecase.NewsUseCases
import dev.rranndt.article.util.UiComponent
import dev.rranndt.article.util.UiText
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Android Studio Giraffe
 * User: rranndt
 * Date: Mon, September 11
 */
@HiltViewModel
class DetailViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases,
    private val application: Application
) : ViewModel() {
    private val _uiComponent = MutableStateFlow<UiComponent?>(null)
    val uiComponent: StateFlow<UiComponent?> = _uiComponent.asStateFlow()

    fun onEvent(event: DetailEvent) {
        when (event) {
            is DetailEvent.InsertDeleteArticle -> {
                viewModelScope.launch {
                    val article = newsUseCases.getArticle(url = event.article.url ?: "")
                    if (article == null) {
                        insertArticle(article = event.article)
                    } else {
                        deleteArticle(article = event.article)
                    }
                }
            }

            is DetailEvent.UpdateBookmarkArticle -> {
                viewModelScope.launch {
                    newsUseCases.updateBookmarkArticle(event.article, event.isBookmark)
                }
            }
        }
    }

    private suspend fun insertArticle(article: Article) {
        newsUseCases.insertArticle(article = article)
        _uiComponent.value = UiComponent.SnackBar(
            UiText.Resource(R.string.snackbar_item_bookmarked).asString(application)
        )
    }

    private suspend fun deleteArticle(article: Article) {
        newsUseCases.deleteArticle(article = article)
        _uiComponent.value = UiComponent.SnackBar(
            UiText.Resource(R.string.snackbar_item_removed).asString(application)
        )
    }
}