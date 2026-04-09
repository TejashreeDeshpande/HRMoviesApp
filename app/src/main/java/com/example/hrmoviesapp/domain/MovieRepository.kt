package com.example.hrmoviesapp.domain

import com.example.hrmoviesapp.data.MovieData
import com.example.hrmoviesapp.presentation.DefaultMovies

class MovieRepository(private val api: MovieApi) {
    suspend fun getMovies(query: String): List<MovieData> {
        return try {
            val response =
                if (query.isBlank())
                    api.fetchMovies().movieData
                else
                    api.searchMovies(query).movieData

            response.ifEmpty {
                DefaultMovies.list
            }
        } catch (e: Exception) {
            DefaultMovies.list
        }
    }
}