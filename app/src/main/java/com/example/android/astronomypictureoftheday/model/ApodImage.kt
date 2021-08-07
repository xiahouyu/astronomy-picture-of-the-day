package com.example.android.astronomypictureoftheday.model

data class ApodImage(
    val date: String,
    val title: String,
    val type: String,
    val description: String,
    val url: String
)