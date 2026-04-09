package com.example.hrmoviesapp.data

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("data")
    val movieData: List<MovieData>
)