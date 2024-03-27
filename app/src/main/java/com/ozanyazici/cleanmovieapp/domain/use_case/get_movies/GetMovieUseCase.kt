package com.ozanyazici.cleanmovieapp.domain.use_case.get_movies

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import com.ozanyazici.cleanmovieapp.data.remote.dto.toMovieList
import com.ozanyazici.cleanmovieapp.domain.model.Movie
import com.ozanyazici.cleanmovieapp.domain.repository.MovieRepository
import com.ozanyazici.cleanmovieapp.util.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOError
import javax.inject.Inject

class GetMovieUseCase @Inject constructor(private val repository: MovieRepository) {

    // UseCaselerin içerinsinde tek bir tane ana fonksiyon olur.
    // Böylelikle bu fonksiyon değişirse sadece burayı değiştirerek işin içinden çıkabileceğiz.
    // O nedenle useCasemizde birden fazla fonksiyon olursa o usecasei bölmeyi düşünmek mantıklı olabilir.

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun executeGetMovies(search: String): Flow<Resource<List<Movie>>> = flow {
        try {
            emit(Resource.Loading())
            val movieList = repository.getMovies(search)
            // Response parametresi aradığımız anahtar kelimeye uygun film bulundu mu bulunmadımı bunun cevabını taşıyor. Bunun kontrolünü yapıyoruz.
            if(movieList.Response == "True") {
                emit(Resource.Success(movieList.toMovieList()))
            } else if(movieList.Response == "False") {
                emit(Resource.Error(message = "No movie found!"))
            } else {
                emit(Resource.Loading())
            }
        } catch (e: IOError) {
            emit(Resource.Error(message = "No internet connection"))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Error"))
        }
    }
}