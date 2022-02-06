package com.example.kotlinomdbapp.adapter

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
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
        holder.bindUrl(searchData.Search[position].imdbID)
    }

    class HomeViewHolder(private val binding: MovieCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindTitle(title: String) = with(binding) {
            movieTitle.text = title
        }

        fun bindDate(date: String) = with(binding) {
            movieYear.text = date
        }

        fun bindPoster(poster_url: String) = with(binding) {
            Picasso.get().load(poster_url).into(posterImage)
        }

        fun bindUrl(imdb_id: String) = with(binding) {
             posterImage.setOnClickListener {
                 val url = "https://www.imdb.com/title/${imdb_id}"
                 val intent = Intent(Intent.ACTION_VIEW)
                 intent.data = Uri.parse(url)
                 startActivity(root.context, intent, Bundle.EMPTY)
             }
        }
        companion object {
            fun newInstance(parent: ViewGroup) = MovieCardBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ).let { HomeViewHolder(it) }
        }
    }
}