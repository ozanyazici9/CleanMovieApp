package com.ozanyazici.cleanmovieapp.presentation.movie_detail

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozanyazici.cleanmovieapp.domain.use_case.get_movie_detail.GetMovieDetailsUseCase
import com.ozanyazici.cleanmovieapp.util.Constants.IMDB_ID
import com.ozanyazici.cleanmovieapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import retrofit2.Response
import javax.inject.Inject

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    // SavedStateHandle ViewModel'lerin yaşam döngüsüyle ilgili verileri korumak ve yeniden oluşturulduğunda
    // bu verileri geri yüklemek için kullanılan bir araçtır. Routelar ile de veri gönderildiğinde almamızı sağlayan bir yapı.
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _state = mutableStateOf<MovieDetailState>(MovieDetailState())
    val state: State<MovieDetailState> = _state

    init {
        savedStateHandle.get<String>(IMDB_ID)?.let {
            getMovieDetail(it)
        }
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    private fun getMovieDetail(imdbId: String) {
        getMovieDetailsUseCase.executeGetMovieDetails(imdbId = imdbId).onEach{
            _state.value = _state.value.copy(isLoading = true)
            when(it) {
                is Resource.Success -> {
                    _state.value = _state.value.copy(movie = it.data)
                    _state.value = _state.value.copy(isLoading = false)
                }
                is Resource.Loading -> {
                    _state.value = _state.value.copy(isLoading = true)
                    _state.value = _state.value.copy(error = "")
                }
                is Resource.Error -> {
                    _state.value = _state.value.copy(error = it.message ?: "Error!")
                    _state.value = _state.value.copy(isLoading = false)
                }
            }
        }.launchIn(viewModelScope)
    }
}