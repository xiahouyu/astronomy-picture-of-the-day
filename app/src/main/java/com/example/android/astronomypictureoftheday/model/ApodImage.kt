package com.example.android.astronomypictureoftheday.model

import android.os.Parcel
import android.os.Parcelable


data class ApodImage(
    val date: String?,
    val title: String?,
    val type: String?,
    val description: String?,
    val url: String?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(date)
        parcel.writeString(title)
        parcel.writeString(type)
        parcel.writeString(description)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ApodImage> {
        override fun createFromParcel(parcel: Parcel): ApodImage {
            return ApodImage(parcel)
        }

        override fun newArray(size: Int): Array<ApodImage?> {
            return arrayOfNulls(size)
        }
    }
}