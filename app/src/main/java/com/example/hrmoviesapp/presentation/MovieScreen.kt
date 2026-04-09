package com.example.hrmoviesapp.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.hrmoviesapp.data.MovieData

@Composable
fun MovieScreen(
    moviePages: List<MovieData>,
    searchText: String,
    onSearchTextChange: (String) -> Unit,
    onSearch: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {

        // Search Row (TextField + Button)
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {

            TextField(
                value = searchText,
                onValueChange = onSearchTextChange,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                placeholder = { Text("Search movies") },
                trailingIcon = {
                    if (searchText.isNotEmpty()) {
                        IconButton(
                            onClick = {
                                onSearchTextChange("")
                                onSearch()
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Clear search"
                            )
                        }
                    }
                }
            )

            Button(
                onClick = onSearch
            ) {
                Text("Search")
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Movie List
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