package com.ozanyazici.cleanmovieapp.presentation.movies

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozanyazici.cleanmovieapp.domain.use_case.get_movies.GetMovieUseCase
import com.ozanyazici.cleanmovieapp.presentation.movies.MoviesEvent
import com.ozanyazici.cleanmovieapp.presentation.movies.MoviesState
import com.ozanyazici.cleanmovieapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
class MoviesViewModel @Inject constructor(private val getMovieUseCase: GetMovieUseCase) : ViewModel() {

    // başlangıçta _state değişkenine MoviesState sınıfının bir örneğini atıyoruz.
    private val _state = mutableStateOf<MoviesState>(MoviesState())
    val state: State<MoviesState> = _state

    private var job: Job? = null

    init {
        getMovies(_state.value.search)
    }


    private fun getMovies(search: String) {
        job?.cancel()

        job = getMovieUseCase.executeGetMovies(search = search).onEach {
            when(it) {
                is Resource.Success -> {
                    _state.value = MoviesState(movies = it.data ?: emptyList())
                }

                // copy fonksiyonu, bir veri sınıfının bir kopyasını oluştururken kullanılır.
                // Bu yöntem, önceki durumu korur ve yanlışlıkla orijinal veriyi değiştirme riskini azaltır.
                // Performans açısından, yeniden bir nesne oluşturmak yerine copy fonksiyonunu kullanmak genellikle daha iyidir,
                // çünkü bu yöntemle veriyi güncellemek mevcut veriyi değiştirmekten daha güvenlidir ve hata yapma olasılığını azaltır.
                // Burda da _state e atadığımız MoviesState sınıfının örneğini kopyalıyoruz.
                // o yüzden yukarıdakki gibi değil böyle yaparak devam edeceğim.
                is Resource.Error -> {
                    _state.value = _state.value.copy(error = it.message ?: "Error!")
                }

                is Resource.Loading -> {
                    _state.value = _state.value.copy(isLoading = true)
                }
            }

        }.launchIn(viewModelScope)
    }

    // Kullanıcı etkileşime geçtiğinde çağrılacak fonksiyon bu view kısmında parametre olarak eventi alıp
    // eventi ne olduğuna göre burada işlemler yapacağız.
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun onEvent(event: MoviesEvent) {
        when(event) {
            is MoviesEvent.Search -> {
                getMovies(event.searchString)
            }
        }
    }
}














