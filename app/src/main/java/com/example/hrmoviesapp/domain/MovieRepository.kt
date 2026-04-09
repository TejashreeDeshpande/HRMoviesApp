package com.example.hrmoviesapp.domain

import com.example.hrmoviesapp.data.MovieData

class MovieRepository(private val api: MovieApi) {
    suspend fun getMovies(query: String): List<MovieData> {
        return if (query.isBlank())
            api.fetchMovies().movieData
        else
            api.searchMovies(query).movieData
    }
}