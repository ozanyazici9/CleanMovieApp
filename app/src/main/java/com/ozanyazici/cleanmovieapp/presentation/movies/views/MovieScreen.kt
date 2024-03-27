package com.ozanyazici.cleanmovieapp.presentation.movies.views

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ozanyazici.cleanmovieapp.presentation.Screen
import com.ozanyazici.cleanmovieapp.presentation.movies.MoviesEvent
import com.ozanyazici.cleanmovieapp.presentation.movies.MoviesViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay


@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun MovieScreen(navController: NavController, viewModel: MoviesViewModel = hiltViewModel()) {

    val state = viewModel.state.value

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black)) {
        Column {
            MovieSearchBar(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp, horizontal = 5.dp),
                hint = "Batman",
                onSearch = {
                    viewModel.onEvent(MoviesEvent.Search(it.trim()))
                },
                stateError = state.error,
                stateLoading = state.isLoading
            )
            
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.movies) {movie ->
                    MovieListRow(movie = movie, onItemClick = {
                        navController.navigate(Screen.MovieDetailScreen.route+"/${movie.imdbID}")
                    } )
                }
            }
        }
    }
}

@Composable
fun MovieSearchBar(
    modifier: Modifier,
    hint: String = "",
    onSearch: (String) -> Unit = {},
    stateError: String,
    stateLoading: Boolean
) {
    var text by remember { mutableStateOf("") }

    var isHintDisplayed by remember {
        mutableStateOf(hint != "")
    }

    var isErrorDisplayed by remember {
        mutableStateOf(stateError.isNotEmpty())
    }

    var isLoadingDisplayed by remember {
        mutableStateOf(stateLoading != false)
    }

    Box(modifier = modifier) {

        TextField(
            value = text,
            onValueChange = {
                text = it
                if (text.isEmpty()) {
                    isErrorDisplayed = false
                    isLoadingDisplayed = false

                } else {
                        onSearch(text)
                        isErrorDisplayed = stateError.isNotEmpty()
                }
            },
            // Klavyede tik işaretine veya enter a vb. basıldığında ne yapılacağını yazıyoruz.
            keyboardActions = KeyboardActions(onDone = {
                isErrorDisplayed = stateError.isNotEmpty()
                onSearch(text)
            }),
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color.Black),
            shape = RoundedCornerShape(23.dp),
            modifier = Modifier
                .background(Color.White, CircleShape)
                .fillMaxWidth()
                .height(50.dp)
                .shadow(5.dp, CircleShape)
                .onFocusChanged {
                    isHintDisplayed = it.isFocused != true && text.isEmpty()
                }
        )
        if (isHintDisplayed) {
            isLoadingDisplayed = false
            isErrorDisplayed = false
            Text(
                text = hint,
                color = Color.LightGray,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 15.dp)
            )
        }
    }
    if (isErrorDisplayed) {
        isLoadingDisplayed = false
        Text(
            text = stateError,
            color = Color.Red,
            textAlign = TextAlign.Center,
            style = TextStyle(fontSize = 20.sp),
            modifier = Modifier.padding(horizontal = 125.dp, vertical = 10.dp)
        )
    }

    if (isLoadingDisplayed) {
        CircularProgressIndicator(modifier = Modifier.padding(horizontal = 185.dp, vertical = 10.dp),
            color = Color.Red
        )
    }


}