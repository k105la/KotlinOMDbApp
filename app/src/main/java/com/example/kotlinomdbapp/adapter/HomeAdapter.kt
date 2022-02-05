package com.example.kotlinomdbapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinomdbapp.databinding.MovieCardBinding
import com.example.kotlinomdbapp.model.MovieModel

class HomeAdapter(private val searchData: MovieModel)
    : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    override fun getItemCount() = searchData.Search.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = HomeViewHolder.newInstance(parent)

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    class HomeViewHolder(binding: MovieCardBinding)
        : RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun newInstance(parent: ViewGroup) = MovieCardBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ).let { HomeViewHolder(it)}
        }
    }
}