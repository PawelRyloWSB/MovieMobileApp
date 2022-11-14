package com.example.moviemobileapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviemobileapp.models.Result

class MovieCardAdapter(val data: MutableList<com.example.moviemobileapp.models.Result>) : RecyclerView.Adapter<PostViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_card, parent, false)
        return PostViewHolder(view)
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

class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val title: TextView = itemView.findViewById(R.id.title)

    fun bindView(data: com.example.moviemobileapp.models.Result) {
        title.text = data.title
    }
}