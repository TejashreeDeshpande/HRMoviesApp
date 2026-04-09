package com.example.hrmoviesapp.presentation

import androidx.compose.foundation.clickable
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.hrmoviesapp.data.MovieData

@Composable
fun MovieItem(movieItem: MovieData,
              onClick: () -> Unit = {}
) {
    ListItem(
        modifier = Modifier.clickable { onClick() },
        headlineContent = { Text(text = movieItem.title) },
        supportingContent = { Text(text = "Year: ${movieItem.year}") }
    )
}