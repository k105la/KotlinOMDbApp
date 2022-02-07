package com.example.kotlinomdbapp.view.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.kotlinomdbapp.databinding.FragmentSplashBinding

class MovieSplash : Fragment() {
    // Initializes FragmentSplashBinding into variable
    private lateinit var movieSplashBinding: FragmentSplashBinding
    // Called by Android once the Fragment should inflate a view.
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // inflate the Fragment's view
        movieSplashBinding = FragmentSplashBinding.inflate(inflater, container, false)
        return movieSplashBinding.root // contains the inflated view.
    }
    // Called once the fragment is ready to be displayed on screen.
    override fun onStart() {
        super.onStart()
        // Navigates to home fragment after splash screen displays
        Handler(Looper.getMainLooper()).postDelayed({
            val action = MovieSplashDirections.actionMovieSplashToHomeFragment()
            findNavController().navigate(action)
        }, 4500)
    }
}