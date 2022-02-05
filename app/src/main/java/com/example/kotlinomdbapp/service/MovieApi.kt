package com.example.kotlinomdbapp.service

import com.example.kotlinomdbapp.model.MovieModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi{
    @GET("/")
    suspend fun getMovies(@Query("s") s : String,
                          @Query("apikey") apikey: String = "b840d385",
                          @Query("type") type : String = "movie" ): Response<MovieModel>
    companion object {
        const val BASE_URL = "https://www.omdbapi.com/"
    }
}