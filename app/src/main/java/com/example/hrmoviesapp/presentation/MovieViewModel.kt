package com.example.hrmoviesapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hrmoviesapp.domain.MovieRepository
import com.example.hrmoviesapp.domain.MovieUiState
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MovieViewModel(
    private val repository: MovieRepository
) : ViewModel() {
    private val _searchText = MutableStateFlow("")
    val searchText: StateFlow<String> = _searchText

    private val searchQuery = MutableStateFlow("")

    private val _uiState = MutableStateFlow<MovieUiState>(MovieUiState.Loading)
    val uiState: StateFlow<MovieUiState> = _uiState

    init {
        observeSearch()

        searchQuery.update { "" }
    }

    fun onSearchQueryChanged() {
        searchQuery.value = _searchText.value
    }

    fun updateSearchText(text: String) {
        _searchText.value = text
    }

    @OptIn(FlowPreview::class)
    private fun observeSearch() {
        searchQuery
            .debounce(500)
            .distinctUntilChanged()
            .onEach { query ->
                fetchMovies(query)
            }
            .launchIn(viewModelScope)
    }

    private fun fetchMovies(query: String) {
        viewModelScope.launch {

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
}