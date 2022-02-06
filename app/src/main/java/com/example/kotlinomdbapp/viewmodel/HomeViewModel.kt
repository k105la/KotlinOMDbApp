package com.example.kotlinomdbapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinomdbapp.service.RetrofitInstance
import com.example.kotlinomdbapp.util.ViewState
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val _movies = MutableLiveData<ViewState>(ViewState.Loading)
    val movies: LiveData<ViewState> get() = _movies

    init {
        viewModelScope.launch {
            initHomeViewModel()
        }
    }

    suspend fun initHomeViewModel(s_query: String = "Batman"): Boolean {
        var success: Boolean
        val state = try {
            val movies = RetrofitInstance.api.getMovies(s_query)
            success = true
            ViewState.Success(movies)
        } catch (ex: Exception) {
            success = false
            ViewState.Error(ex.message ?: "Error something is wrong")
        }
        _movies.value = state
        return success
    }
}