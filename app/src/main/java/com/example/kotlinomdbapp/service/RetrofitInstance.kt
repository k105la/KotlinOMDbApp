package com.example.kotlinomdbapp.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// The Retrofit object generates an implementation of the MovieApi interface
object RetrofitInstance {
    val api: MovieApi by lazy {
        Retrofit.Builder()
            .baseUrl(MovieApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieApi::class.java)
    }
}