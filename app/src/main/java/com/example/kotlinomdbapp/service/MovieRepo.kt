package com.example.kotlinomdbapp.service

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object MovieRepo {
    private val movieService by lazy {  RetrofitInstance().movieService }

    suspend fun getMovies(s_query: String) = withContext(Dispatchers.IO) {
        movieService.getMovies(s_query)
    }

    suspend fun checkForInvalidInput(s_query: String) = withContext(Dispatchers.IO) {
         movieService.getStatus(s_query)
    }
}