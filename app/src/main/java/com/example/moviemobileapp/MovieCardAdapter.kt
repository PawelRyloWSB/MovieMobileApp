package com.example.moviemobileapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviemobileapp.models.Result
import com.squareup.picasso.Picasso

class MovieCardAdapter(val data: MutableList<com.example.moviemobileapp.models.Result>, val movieDetailsInterface : MovieDetailsInterface ) : RecyclerView.Adapter<PostViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_card, parent, false)

        return PostViewHolder(view, movieDetailsInterface)
    }

    override fun onBindViewHolder(
        holder: PostViewHolder,
        position: Int
    ) {
        return holder.bindView(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}

class PostViewHolder(itemView: View, val movieDetailsInterface: MovieDetailsInterface) : RecyclerView.ViewHolder(itemView) {
    private val posterUrl = "https://image.tmdb.org/t/p/w300";
    private val title: TextView = itemView.findViewById(R.id.title)
    private val moviePoster: ImageView = itemView.findViewById(R.id.movie_poster) as ImageView

    fun bindView(data: com.example.moviemobileapp.models.Result) {
        title.text = data.title
        Picasso.get().load(posterUrl + data.poster_path).into(moviePoster)

        itemView.setOnClickListener {
            movieDetailsInterface.onClick(data)
        }

    }
}

interface MovieDetailsInterface {
    fun onClick(data: Result)
}