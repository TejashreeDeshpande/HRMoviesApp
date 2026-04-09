package com.example.hrmoviesapp.data

import com.google.gson.annotations.SerializedName

//"Title": "Waterworld",
//"Year": 1995,
//"imdbID": "tt0114898"
data class MovieData(
    @SerializedName("Title")
    val title: String,

    @SerializedName("Year")
    val year: Int,

    @SerializedName("imdbID")
    val imdbID: String
)