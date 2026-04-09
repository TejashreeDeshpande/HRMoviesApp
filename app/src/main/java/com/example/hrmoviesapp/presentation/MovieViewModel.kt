package com.example.hrmoviesapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hrmoviesapp.data.MovieData
import com.example.hrmoviesapp.domain.MovieRepository
import com.example.hrmoviesapp.domain.MovieUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieViewModel(
    private val repository: MovieRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<MovieUiState>(MovieUiState.Loading)
    val uiState: StateFlow<MovieUiState> = _uiState

    init {
        fetchMovies()
    }

    fun fetchMovies() {
        viewModelScope.launch {
            _uiState.value = MovieUiState.Loading

            try {
                val movies = repository.fetchMovies()
                _uiState.value = if (movies.isNotEmpty()) {
                    MovieUiState.Success(movies = movies)
                } else {
                    MovieUiState.Empty
                }
            } catch (e: Exception) {
                _uiState.value = MovieUiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}
