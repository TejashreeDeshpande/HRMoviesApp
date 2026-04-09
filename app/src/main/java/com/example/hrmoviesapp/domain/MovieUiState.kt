package com.example.hrmoviesapp.domain

import com.example.hrmoviesapp.data.MovieData

sealed class MovieUiState {
    object Loading: MovieUiState()
    data class Success(val movies: List<MovieData>): MovieUiState()
    data class Error(val message: String): MovieUiState()
    object Empty: MovieUiState()
}