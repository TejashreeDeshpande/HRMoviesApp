package com.example.hrmoviesapp.domain

import com.example.hrmoviesapp.data.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("api/movies/")
    suspend fun fetchMovies(): MovieResponse

    @GET("api/movies/search/")
    suspend fun searchMovies(@Query("Title") query: String): MovieResponse
}