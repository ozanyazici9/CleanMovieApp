package com.ozanyazici.cleanmovieapp.domain.repository

import com.ozanyazici.cleanmovieapp.data.remote.dto.MovieDetailDto
import com.ozanyazici.cleanmovieapp.data.remote.dto.MoviesDto

interface MovieRepository {
    suspend fun getMovies(search: String): MoviesDto
    suspend fun getMovieDetail(imdbId: String): MovieDetailDto
}