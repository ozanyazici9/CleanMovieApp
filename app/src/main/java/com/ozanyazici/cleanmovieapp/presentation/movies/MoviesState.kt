package com.ozanyazici.cleanmovieapp.presentation.movies

import com.ozanyazici.cleanmovieapp.domain.model.Movie

// Bu sınıfın görevi filmlerin gösterileceği ekranın durmunu tutmak ve bize bildirmek.

data class MoviesState(
    val isLoading: Boolean = false,
    val movies: List<Movie> = emptyList(),
    val error: String = "",
    // Burada direkt batman değerini verdiğimiz için başlangıçta hemen batman filmlerini göreceğiz.
    val search: String = "batman"
)
