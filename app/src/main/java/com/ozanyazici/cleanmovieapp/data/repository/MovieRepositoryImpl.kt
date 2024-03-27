package com.ozanyazici.cleanmovieapp.data.repository

import com.ozanyazici.cleanmovieapp.data.remote.MovieAPI
import com.ozanyazici.cleanmovieapp.data.remote.dto.MovieDetailDto
import com.ozanyazici.cleanmovieapp.data.remote.dto.MoviesDto
import com.ozanyazici.cleanmovieapp.domain.repository.MovieRepository
import javax.inject.Inject

// data paketinde ayrıca bir repositorymiz var çünkü domain paketinde business logic in genel hatlarını çıkarıyoruz burada ise uyguluyoruz.
// bu kodumuzu daha modüler, bakımı daha kolay ve bağımlılıkları daha iyi yönetebileceğimiz bir şekilde düzenlemeyi sağlar.

class MovieRepositoryImpl @Inject constructor(private val api: MovieAPI) : MovieRepository {
    override suspend fun getMovies(search: String): MoviesDto {
        return api.getMovies(searchString = search)
    }

    override suspend fun getMovieDetail(imdbId: String): MovieDetailDto {
        return api.getMovieDetail(imdbId = imdbId)
    }
}