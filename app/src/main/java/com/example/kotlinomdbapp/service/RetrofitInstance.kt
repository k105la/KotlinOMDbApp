package com.example.kotlinomdbapp.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// The Retrofit object generates an implementation of the MovieApi interface
class RetrofitInstance {
    private val rf = Retrofit.Builder()
            .baseUrl(MovieApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val movieService: MovieApi by lazy { rf.create(MovieApi::class.java) }
}


