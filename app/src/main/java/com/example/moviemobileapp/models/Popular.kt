package com.example.moviemobileapp.models

class Popular(
    val page: Int,
    val results: MutableList<Result>,
    val total_pages: Int,
    val total_results: Int
)
