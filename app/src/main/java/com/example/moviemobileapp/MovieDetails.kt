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
        binding.movieTitle.text = getString(R.string.orginalTitle, data.title)
        binding.overview.text = getString(R.string.overview, data.overview)
        binding.originalLanguage.text = getString(R.string.originalLanguage, data.original_language)
        binding.voteAverage.text = getString(R.string.vote_average, data.vote_average)
        binding.releaseDate.text = getString(R.string.release_date, data.release_date)
    }
}