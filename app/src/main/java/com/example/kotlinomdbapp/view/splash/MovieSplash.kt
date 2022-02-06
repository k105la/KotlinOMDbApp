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
    private lateinit var movieSplashBinding: FragmentSplashBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        movieSplashBinding = FragmentSplashBinding.inflate(inflater, container, false)
        return movieSplashBinding.root
    }

    override fun onStart() {
        super.onStart()
        Handler(Looper.getMainLooper()).postDelayed({
            val action = MovieSplashDirections.actionMovieSplashToHomeFragment()
                        findNavController().navigate(action)
        },3000)
    }
}