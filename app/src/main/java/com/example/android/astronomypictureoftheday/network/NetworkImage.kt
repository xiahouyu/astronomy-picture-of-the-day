package com.example.android.astronomypictureoftheday.network

import com.example.android.astronomypictureoftheday.database.ApodEntity
import com.example.android.astronomypictureoftheday.model.ApodImage
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class NetworkImage(
    val date: String,
    val title: String,
    @Json(name = "media_type")
    val type: String,
    @Json(name = "explanation")
    val description: String,
    val url: String
)


fun List<NetworkImage>.asModel(): List<ApodImage> {
    return map{
        ApodImage(
            date = it.date,
            title = it.title,
            type = it.type,
            description = it.description,
            url = it.url
        )
    }
}

fun List<NetworkImage>.asEntity(): List<ApodEntity> {
    return map{
        ApodEntity(
            date = it.date,
            title = it.title,
            type = it.type,
            description = it.description,
            url = it.url
        )
    }
}