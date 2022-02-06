package com.example.kotlinomdbapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.kotlinomdbapp.util.ViewState
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinomdbapp.adapter.HomeAdapter
import com.example.kotlinomdbapp.databinding.FragmentHomeBinding
import com.example.kotlinomdbapp.model.MovieModel
import com.example.kotlinomdbapp.viewmodel.HomeViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private val viewModel by lazy { HomeViewModel() }
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        handleSearch()
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
    }

    private fun handleSearch() = with(binding) {
        searchMovie.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(q: String?): Boolean {
                lifecycleScope.launch {
                    if (viewModel.checkForInvalidInput(q ?: "")) {
                        if (viewModel.initHomeViewModel(q ?: "")) {
                            searchMovie.setQuery("", false)
                            searchMovie.clearFocus()
                            Snackbar.make(root, "Success!", Snackbar.LENGTH_SHORT).show()
                        } else {
                            searchMovie.setQuery("", false)
                            searchMovie.clearFocus()
                        }
                    } else {
                        Snackbar.make(binding.root,
                            "Movie not found please search again.",
                            Snackbar.LENGTH_LONG).show()
                    }
                }
                return false
            }

            override fun onQueryTextChange(p0: String?) = false
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}