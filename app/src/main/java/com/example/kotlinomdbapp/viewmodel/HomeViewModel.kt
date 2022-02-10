package com.example.kotlinomdbapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinomdbapp.service.MovieRepo
import com.example.kotlinomdbapp.service.MovieRepo.checkForInvalidInput
import com.example.kotlinomdbapp.service.RetrofitInstance
import com.example.kotlinomdbapp.util.ViewState
import kotlinx.coroutines.launch
import retrofit2.await

class HomeViewModel : ViewModel() {
    // Creates a MutableLiveData initialized with the ViewState.Loading
    private val _movies = MutableLiveData<ViewState>(ViewState.Loading)
    // LiveData which publicly exposes movies
    val movies: LiveData<ViewState> get() = _movies
    // Initialization code
    init {
        // Starts coroutines
        viewModelScope.launch {
            initHomeViewModel()
        }
    }

    suspend fun initHomeViewModel(s_query: String = "Batman") {
        // State variable which holds final state
        val state = try {
            // API call to get MoviesModel
            val movies = MovieRepo.getMovies(s_query)
            // Success state
            ViewState.Success(movies)
        } catch (ex: Exception) {
            // Error state
            ViewState.Error(ex.message ?: "Error something is wrong")
        }
        _movies.value = state // Save state to movies variable
    }
}