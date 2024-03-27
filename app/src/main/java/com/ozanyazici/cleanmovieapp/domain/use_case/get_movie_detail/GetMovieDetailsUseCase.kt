package com.ozanyazici.cleanmovieapp.domain.use_case.get_movie_detail

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import com.ozanyazici.cleanmovieapp.data.remote.dto.toMovieDetail
import com.ozanyazici.cleanmovieapp.data.remote.dto.toMovieList
import com.ozanyazici.cleanmovieapp.domain.model.Movie
import com.ozanyazici.cleanmovieapp.domain.model.MovieDetail
import com.ozanyazici.cleanmovieapp.domain.repository.MovieRepository
import com.ozanyazici.cleanmovieapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOError
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(private val repository: MovieRepository) {

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun executeGetMovieDetails(imdbId: String): Flow<Resource<MovieDetail>> = flow {
        try {
            emit(Resource.Loading())
            val movieDetail = repository.getMovieDetail(imdbId = imdbId)
            // Response parametresi aradığımız anahtar kelimeye uygun film bulundu mu bulunmadımı bunun cevabını taşıyor.
            // Bunun kontrolünü yapıyoruz. Gelen imdbId ye göre film detaylarını çekiyoruz o yüzden aslında burada
            // response kontrol etmemize gerek yok ama ilave güvenlik olarak kalsın.
            if(movieDetail.Response.equals("True")) {
                emit(Resource.Success(movieDetail.toMovieDetail()))
            } else {
                emit(Resource.Error(message = "No movie found!"))
            }
        } catch (e: IOError) {
            emit(Resource.Error(message = "No internet connection"))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Error"))
        }
    }
}