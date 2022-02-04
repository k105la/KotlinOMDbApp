package com.example.kotlinomdbapp.model

data class MovieModel(
    val Response: String,
    val Search: List<Search>,
    val totalResults: String
)