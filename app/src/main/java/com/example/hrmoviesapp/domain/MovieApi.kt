package com.example.hrmoviesapp.domain

import com.example.hrmoviesapp.data.MovieResponse
import retrofit2.http.GET

interface MovieApi {
    @GET("api/movies/")
    suspend fun fetchMovies(): MovieResponse

    @GET("api/movies/search/")
    suspend fun searchMovies(query: String): MovieResponse
}