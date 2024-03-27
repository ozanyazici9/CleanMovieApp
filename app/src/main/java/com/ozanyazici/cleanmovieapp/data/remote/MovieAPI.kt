package com.ozanyazici.cleanmovieapp.data.remote

import com.ozanyazici.cleanmovieapp.data.remote.dto.MovieDetailDto
import com.ozanyazici.cleanmovieapp.data.remote.dto.MoviesDto
import com.ozanyazici.cleanmovieapp.util.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {

    //https://omdbapi.com/?apikey=250901b2&i=tt1877830
    //https://omdbapi.com/?apikey=250901b2&s=batman

    // Base url den sonra herhangi bir endpoint adresi olmadığı için get içerisinde belirtmemize gerek yok.
    // Base url den sonra direkt parametreleri koyacağız.
    @GET(".")
    suspend fun getMovies(
        @Query("s") searchString: String,
        @Query("apikey") apiKey: String = API_KEY
    ): MoviesDto

    @GET(".")
    suspend fun getMovieDetail(
        @Query("i") imdbId: String,
        @Query("apikey") apiKey: String = API_KEY
    ): MovieDetailDto
}