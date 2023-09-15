package dev.rranndt.article.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.rranndt.article.domain.usecase.NewsUseCases
import dev.rranndt.article.util.DataState
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Android Studio Giraffe
 * User: rranndt
 * Date: Sun, September 10
 */
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val useCases: NewsUseCases
) : ViewModel() {
    private val _state = MutableStateFlow(SearchState())
    val state: StateFlow<SearchState> = _state.asStateFlow()

    private var searchJob: Job? = null

    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.OnHeadlineNewsClick -> {
                _state.update {
                    it.copy(selectedArticle = event.article)
                }
            }

            is SearchEvent.OnSearchQueryChanged -> {
                _state.update {
                    it.copy(searchQuery = event.searchQuery)
                }
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(1000)
                    searchNews(query = _state.value.searchQuery)
                }
            }

            is SearchEvent.OnSearchQueryFilled -> searchNews(query = _state.value.searchQuery)

            SearchEvent.OnCloseIconClick -> {
                _state.update {
                    it.copy(articles = emptyList())
                }
            }
        }
    }

    private fun searchNews(query: String) {
        if (query.isEmpty()) return
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            useCases.searchNews(query).collect { result ->
                _state.value = when (result) {
                    is DataState.Success -> {
                        _state.value.copy(
                            articles = result.data ?: emptyList(),
                            isLoading = false,
                            error = null
                        )
                    }

                    is DataState.Error -> {
                        _state.value.copy(
                            articles = emptyList(),
                            isLoading = false,
                            error = result.message
                        )
                    }
                }
            }
        }
    }
}