package com.example.hrmoviesapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hrmoviesapp.domain.MovieRepository
import com.example.hrmoviesapp.domain.MovieUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MovieViewModel(
    private val repository: MovieRepository
) : ViewModel() {

    private val searchQuery = MutableStateFlow("")

    private val _uiState = MutableStateFlow<MovieUiState>(MovieUiState.Loading)
    val uiState: StateFlow<MovieUiState> = _uiState

    init {
        observeSearch()
        viewModelScope.launch {
            searchQuery.value = ""
        }
    }

    fun onSearchQueryChanged(query: String) {
        viewModelScope.launch {
            searchQuery.value = query
        }
    }

    private fun observeSearch() {
        viewModelScope.launch {
            searchQuery
                .debounce(500)
                .distinctUntilChanged()
                .collectLatest { query ->
                    fetchMovies(query)
                }
        }
    }

    private suspend fun fetchMovies(query: String) {
        _uiState.value = MovieUiState.Loading

        try {
            val movies = repository.getMovies(query)

            _uiState.value = when {
                movies.isEmpty() -> MovieUiState.Empty
                else -> MovieUiState.Success(movies)
            }

        } catch (e: Exception) {
            _uiState.value = MovieUiState.Error(
                e.message ?: "Failed to load movies"
            )
        }
    }
}