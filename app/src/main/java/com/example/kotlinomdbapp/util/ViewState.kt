package com.example.kotlinomdbapp.util

import com.example.kotlinomdbapp.model.MovieModel
import retrofit2.Response

// Different states in the application.
sealed class ViewState {
    object Loading : ViewState()
    data class Success(val movies: Response<MovieModel>) : ViewState()
    data class Error(val error: String) : ViewState()
}