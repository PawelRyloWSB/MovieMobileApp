package com.example.moviemobileapp

import androidx.appcompat.app.AppCompatActivity
import com.example.moviemobileapp.models.Popular
import android.os.Bundle
import com.example.moviemobileapp.databinding.ActivityMainBinding
import com.google.gson.Gson
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fetchPopularMoviesData().start()
    }

    private fun fetchPopularMoviesData(): Thread {
        return Thread {
            val url = URL("https://api.themoviedb.org/3/movie/popular?api_key=61d13ec0c9cf5cb4d44ca9442dbf9845")
            val connection = url.openConnection() as HttpURLConnection

            if (connection.responseCode == 200) {
                val inputSystem = connection.inputStream
                val inputStreamReader = InputStreamReader(inputSystem, "UTF-8")
                val request = Gson().fromJson(inputStreamReader, Popular::class.java)
                updateUi(request)
                inputStreamReader.close()
                inputSystem.close()
            }
        }
    }


    private fun updateUi(request: Popular) {
        runOnUiThread {
            kotlin.run {
                binding.textHome.text = request.total_pages.toString()
            }
        }

    }
}