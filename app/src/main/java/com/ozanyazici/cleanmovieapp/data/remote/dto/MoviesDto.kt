package com.ozanyazici.cleanmovieapp.data.remote.dto

import com.ozanyazici.cleanmovieapp.domain.model.Movie

// Biz buradaki ve movieDetailDto daki api den gelen verilerin hepsini kullanmayacağız bu yüzden domain içerisinde
// ayrı bir model paketi oluşturup orada kullanacağımız verileri alıyoruz.

data class MoviesDto(
    val Response: String,
    val Search: List<Search>,
    val totalResults: String
)
// Burada bir extension yazıyoruz. MoviesDto.toMovieList() dediğimiz zaman
// bize domain içerisindeki movie modeli türünde veri içeren bir liste döndürecek.
// Böylelikle sadece kullanacağımız verileri almış oluyoruz.

fun MoviesDto.toMovieList(): List<Movie> {
    // Map bir objeyi başka bir objeye dönüştürür.
    return Search.map { search -> Movie(search.Poster,search.Title,search.Year,search.imdbID) }
}