package com.example.android.astronomypictureoftheday.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.android.astronomypictureoftheday.model.ApodImage

@Entity(tableName = "daily_astronomy_picture_table")
data class ApodEntity(
    @PrimaryKey
    val date: String,
    val title: String,
    val type: String,
    val description: String,
    val url: String
)

fun List<ApodEntity>.asModel(): List<ApodImage> {
    return map {
        ApodImage(
            date = it.date,
            title = it.title,
            type = it.type,
            description = it.description,
            url = it.url
        )
    }
}