package com.example.kotlinomdbapp.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.kotlinomdbapp.util.ViewState
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinomdbapp.adapter.HomeAdapter
import com.example.kotlinomdbapp.databinding.FragmentHomeBinding
import com.example.kotlinomdbapp.model.MovieModel
import com.example.kotlinomdbapp.viewmodel.HomeViewModel

class HomeFragment: Fragment() {
    private lateinit var adapter: HomeAdapter
    private val viewModel by lazy { HomeViewModel() }
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
    }

    private fun initObservers() = with(viewModel) {
        movies.observe(viewLifecycleOwner) { state ->
            binding.rvList.layoutManager = LinearLayoutManager(context)
            binding.loader.isVisible = state is ViewState.Loading
            if (state is ViewState.Success) {
                state.movies.body()?.let { handleSuccess(it) }
            }
            if (state is ViewState.Error) {
                handleError(state.error)
            }
        }
    }

    private fun handleSuccess(movies: MovieModel) {
        binding.rvList.adapter = HomeAdapter(movies)
    }


    private fun handleError(exception: String) {
        Toast.makeText(context, exception, Toast.LENGTH_LONG).show()
        Log.i("ERROR", "Something went wrong")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}