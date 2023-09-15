package dev.rranndt.article.presentation.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.rranndt.article.domain.usecase.NewsUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Android Studio Giraffe
 * User: rranndt
 * Date: Mon, September 11
 */
@HiltViewModel
class BookmarkViewModel @Inject constructor(private val newsUseCases: NewsUseCases) : ViewModel() {
    private val _state = MutableStateFlow(BookmarkState())
    val state: StateFlow<BookmarkState> = _state.asStateFlow()

    init {
        onEvent(BookmarkEvent.GetArticles)
    }

    fun onEvent(event: BookmarkEvent) {
        when (event) {
            is BookmarkEvent.OnHeadlineNewsClick -> {
                _state.update {
                    it.copy(selectedArticle = event.article)
                }
            }

            is BookmarkEvent.DeleteArticle -> {
                viewModelScope.launch {
                    newsUseCases.deleteArticle(article = event.article)
                }
            }

            BookmarkEvent.GetArticles -> {
                newsUseCases.getArticles().onEach { articles ->
                    _state.update {
                        it.copy(articles = articles)
                    }
                }.launchIn(viewModelScope)
            }

            BookmarkEvent.ClearArticle -> {
                viewModelScope.launch {
                    newsUseCases.clearArticle()
                }
            }
        }
    }
}