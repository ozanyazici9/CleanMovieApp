package com.ozanyazici.cleanmovieapp.data.dependencyinjection

import com.ozanyazici.cleanmovieapp.data.remote.MovieAPI
import com.ozanyazici.cleanmovieapp.data.repository.MovieRepositoryImpl
import com.ozanyazici.cleanmovieapp.domain.repository.MovieRepository
import com.ozanyazici.cleanmovieapp.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

// Hilt kullanırken genellikle her bağımlılık için ayrı bir modül oluşturmanıza gerek yoktur.
// Ancak, bazı durumlarda modüllerin kullanılması bağımlılıkları daha iyi yönetmenize ve özelleştirmenize olanak tanır.

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMovieApi(): MovieAPI {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(api: MovieAPI): MovieRepository {
        return MovieRepositoryImpl(api = api)
    }
}