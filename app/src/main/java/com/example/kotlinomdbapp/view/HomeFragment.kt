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
    // Returns an instance of HomeViewModel()
    private val viewModel by lazy { HomeViewModel() }
    // mutable (non-const)
    private var _binding: FragmentHomeBinding? = null
    // immutable (const)
    private val binding get() = _binding!!
    // Called by Android once the Fragment should inflate a view
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // inflate the Fragment's view
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        handleSearch() // Init handle search function
        return binding.root // contains the inflated view.
    }
    // Called after onCreateView() and ensures that the fragment's root view is non-null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
    }
    // Attaches adapter and sets layout manager
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
    // Function that takes MovieModel as input to attach adapter
    private fun handleSuccess(movies: MovieModel) {
        binding.rvList.adapter = HomeAdapter(movies)
    }
    // Function display error message in a snackbar
    private fun handleError(exception: String) = Snackbar.make(binding.root, exception , Snackbar.LENGTH_LONG).show()
    // Function that handles valid and invalid search input
    private fun handleSearch() = with(binding) {
        searchMovie.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(q: String?): Boolean {
                lifecycleScope.launch {
                    if (viewModel.checkForInvalidInput(q ?: "")) {
                        viewModel.initHomeViewModel(q ?: "")
                        searchMovie.setQuery("", false)
                        searchMovie.clearFocus()
                        Snackbar.make(root, "Success!", Snackbar.LENGTH_SHORT).show()
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
    // Called when fragment's view is being destroyed, but the fragment is still kept around.
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}