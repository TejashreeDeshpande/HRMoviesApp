package com.example.hrmoviesapp.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.hrmoviesapp.data.MovieData

@Composable
fun MovieScreen(
    moviePages: List<MovieData>,
    onSearch: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var text by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {

        // 🔍 Search Row (TextField + Button)
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {

            TextField(
                value = text,
                onValueChange = { text = it },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                placeholder = { Text("Search movies") }
            )

            Button(
                onClick = {
                    onSearch(text.trim()) // trim removes leading/trailing spaces
                }
            ) {
                Text("Search")
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // 🎬 Movie List
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(
                items = moviePages,
                key = { it.imdbID }
            ) { movie ->
                MovieItem(movieItem = movie)
                HorizontalDivider()
            }
        }
    }
}