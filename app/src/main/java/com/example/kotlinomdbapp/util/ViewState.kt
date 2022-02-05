package com.example.kotlinomdbapp.util
import com.example.kotlinomdbapp.model.MovieModel
// Different states in the application.
sealed class ViewState {
    object Loading: ViewState()
    data class Success(val movies: MovieModel) : ViewState()
    data class Error(val error: String) : ViewState()
}