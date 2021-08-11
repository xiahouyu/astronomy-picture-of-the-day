package com.example.android.astronomypictureoftheday.model

import android.os.Parcel
import android.os.Parcelable


data class ApodImage(
    val date: String?,
    val title: String?,
    val type: String?,
    val description: String?,
    val url: String?
)