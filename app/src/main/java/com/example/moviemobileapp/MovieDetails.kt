package com.example.moviemobileapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.moviemobileapp.databinding.ActivityMovieDetailsBinding

class MovieDetails : AppCompatActivity() {

    private lateinit var binding:  ActivityMovieDetailsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent.getSerializableExtra("data") as com.example.moviemobileapp.models.Result

        println(data.title)
        binding.movieTitle.setText(data.title)
        binding.overview.setText(data.overview)
    }
}