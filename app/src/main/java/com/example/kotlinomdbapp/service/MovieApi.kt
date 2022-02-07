package com.example.kotlinomdbapp.service

import com.example.kotlinomdbapp.model.MovieModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

// Interface that defines the two GET request
// getMovies - gets Search object that contains movies data
// getSearch - get Response (Boolean) if search was valid
interface MovieApi {
    @GET("/")
    suspend fun getMovies(@Query("s") s : String,
                          @Query("apikey") apikey: String = "b840d385",
                          @Query("type") type : String = "movie" ): Response<MovieModel>
    @GET("/")
    fun getStatus(@Query("s") s : String,
                          @Query("apikey") apikey: String = "b840d385",
                          @Query("type") type : String = "movie" ): Call<MovieModel>
    companion object {
        const val BASE_URL = "https://www.omdbapi.com/"
    }
}