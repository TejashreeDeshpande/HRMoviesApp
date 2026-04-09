package com.example.hrmoviesapp.presentation

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.hrmoviesapp.data.MovieData

@Composable
fun MovieScreen(moviePages: List<MovieData>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(moviePages) { individualMovie ->
            MovieItem(movieItem = individualMovie)
            HorizontalDivider()
        }
    }
}

@Composable
fun MovieItem(movieItem: MovieData) {
    ListItem(
        headlineContent = { Text(text = movieItem.title) },
        supportingContent = { Text(text = "Year: ${movieItem.year}") }
    )
}