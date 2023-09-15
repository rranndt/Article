package dev.rranndt.article.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.rranndt.article.domain.usecase.NewsUseCases
import dev.rranndt.article.util.DataState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Android Studio Giraffe
 * User: rranndt
 * Date: Sat, September 09
 */
@HiltViewModel
class HomeViewModel @Inject constructor(private val useCases: NewsUseCases) : ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.asStateFlow()

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.OnHeadlineNewsClick -> {
                _state.update {
                    it.copy(selectedArticle = event.article)
                }
            }

            is HomeEvent.OnCategoryChanged -> {
                _state.update {
                    it.copy(category = event.category)
                }
                getHeadlineNews(_state.value.category)
            }
        }
    }

    private fun getHeadlineNews(category: String) {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            useCases.getHeadlineNews(category).collect { result ->
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