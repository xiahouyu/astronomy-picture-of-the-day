package com.example.android.astronomypictureoftheday.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.android.astronomypictureoftheday.database.ApodDatabase
import com.example.android.astronomypictureoftheday.database.asModel
import com.example.android.astronomypictureoftheday.model.ApodImage
import com.example.android.astronomypictureoftheday.network.ApodNetworkApi
import com.example.android.astronomypictureoftheday.network.asEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private val startDate = "2021-08-01"

class ApodRepository (private val database: ApodDatabase, val dateString: String = "2021-08-01") {

    val apodImages: LiveData<List<ApodImage>> = Transformations.map(database.apodDao.getImages()) {
        it.asModel()
    }

    val apodImageByDate: LiveData<ApodImage> = Transformations.map(database.apodDao.getByDate(dateString)){
        ApodImage(
            date = it.date,
            title = it.title,
            type = it.type,
            description = it.description,
            url = it.url
        )
    }

    suspend fun refreshImages() {
        withContext(Dispatchers.IO) {
            val networkImages = ApodNetworkApi.networkImages.getApodImages(startDate)
            database.apodDao.insertAll(*networkImages.asEntity())
        }
    }
}