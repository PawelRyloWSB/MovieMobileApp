package com.example.moviemobileapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.example.moviemobileapp.models.Popular
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviemobileapp.databinding.ActivityMainBinding
import com.example.moviemobileapp.models.Result
import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
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
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)

        runOnUiThread {
            kotlin.run {
                recyclerView.apply {
                    layoutManager = LinearLayoutManager(this@MainActivity)
                    adapter = MovieCardAdapter(request.results, object:MovieDetailsInterface{
                        override fun onClick(data: Result) {
                            runBlocking {
                                val intent = Intent(this@MainActivity, MovieDetails::class.java)
                                intent.putExtra("data", data)
                                startActivity(intent)
                            }
                        }
                    })
                }            }
        }

    }
}