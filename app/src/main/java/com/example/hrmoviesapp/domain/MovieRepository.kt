package com.example.hrmoviesapp.domain

import com.example.hrmoviesapp.data.MovieData
import com.example.hrmoviesapp.data.MovieResponse

class MovieRepository(private val api: MovieApi) {
    suspend fun fetchMovies(): List<MovieData> {
        return api.fetchMovies().movieData
    }
}