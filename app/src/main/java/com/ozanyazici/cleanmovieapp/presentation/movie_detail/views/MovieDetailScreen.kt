package com.ozanyazici.cleanmovieapp.presentation.movie_detail.views

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Absolute.Center
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.ozanyazici.cleanmovieapp.presentation.Screen
import com.ozanyazici.cleanmovieapp.presentation.movie_detail.MovieDetailViewModel

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun MovieDetailScreen(viewModel: MovieDetailViewModel = hiltViewModel()) {

    val state = viewModel.state.value

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        state.movie?.let {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(painter = rememberAsyncImagePainter(model = it.Poster),
                    contentDescription = it.Title,
                    modifier = Modifier
                        .padding(16.dp)
                        .size(300.dp, 300.dp)
                        .clip(RectangleShape)
                        .align(Alignment.CenterHorizontally)
                )

                val movieDetails = listOf(
                    it.Title,
                    it.Year,
                    it.Actors,
                    it.Country,
                    it.Director,
                    it.imdbRating
                )
                
                movieDetails.forEach { 
                    Text(
                        text = it,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(14.dp),
                        color = Color.White
                    )
                }
            }
        }
        if (state.error.isNotBlank()) {
            Text(text = state.error,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(14.dp)
                    .align(Alignment.Center))
        }
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center),
                color = Color.Red
            )
        }
    }
}