package com.example.kotlinomdbapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinomdbapp.databinding.MovieCardBinding
import com.example.kotlinomdbapp.model.MovieModel
import com.squareup.picasso.Picasso

class HomeAdapter(private val searchData: MovieModel) :
    RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    override fun getItemCount() = searchData.Search.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        HomeViewHolder.newInstance(parent)

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bindTitle(searchData.Search[position].Title)
        holder.bindDate(searchData.Search[position].Year)
        holder.bindPoster(searchData.Search[position].Poster)
    }

    class HomeViewHolder(private val binding: MovieCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindTitle(title: String) {
            binding.movieTitle.text = title
        }

        fun bindDate(date: String) {
            binding.movieYear.text = date
        }

        fun bindPoster(poster_url: String) {
            Picasso.get().load(poster_url).into(binding.posterImage)
        }

        companion object {
            fun newInstance(parent: ViewGroup) = MovieCardBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ).let { HomeViewHolder(it) }
        }
    }
}