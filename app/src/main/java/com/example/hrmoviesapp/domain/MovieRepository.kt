package com.example.hrmoviesapp.domain

import com.example.hrmoviesapp.data.MovieData
import com.example.hrmoviesapp.presentation.DefaultMovies

class MovieRepository(private val api: MovieApi) {
    suspend fun fetchMovies(): List<MovieData> {
        return try {
            val response = api.fetchMovies().movieData
            response.ifEmpty {
                DefaultMovies.list
            }
        } catch (e: Exception) {
            DefaultMovies.list
        }
    }
}