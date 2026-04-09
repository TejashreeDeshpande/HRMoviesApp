package com.example.hrmoviesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.hrmoviesapp.domain.AppModule
import com.example.hrmoviesapp.domain.MovieUiState
import com.example.hrmoviesapp.domain.MovieViewModelFactory
import com.example.hrmoviesapp.presentation.MovieScreen
import com.example.hrmoviesapp.presentation.MovieViewModel
import com.example.hrmoviesapp.ui.theme.HRMoviesAppTheme

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MovieViewModel> {
        MovieViewModelFactory(AppModule.repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            HRMoviesAppTheme {

                val uiState by viewModel.uiState.collectAsState()

                Scaffold(modifier = Modifier.fillMaxSize()) { padding ->

                    when (uiState) {

                        is MovieUiState.Loading -> {
                            Text("Loading...", modifier = Modifier.padding(padding))
                        }

                        is MovieUiState.Success -> {
                            MovieScreen(
                                moviePages = (uiState as MovieUiState.Success).movies,
                                modifier = Modifier.padding(padding)
                            )
                        }

                        is MovieUiState.Error -> {
                            Text(
                                (uiState as MovieUiState.Error).message,
                                modifier = Modifier.padding(padding)
                            )
                        }

                        is MovieUiState.Empty -> {
                            Text("No movies found", modifier = Modifier.padding(padding))
                        }
                    }
                }
            }
        }
    }
}