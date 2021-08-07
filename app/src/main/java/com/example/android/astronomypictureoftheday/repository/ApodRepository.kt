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

class ApodRepository(private val database: ApodDatabase) {

    val apodImages: LiveData<List<ApodImage>> = Transformations.map(database.apodDao.getImages()) {
        it.asModel()
    }

    suspend fun refreshImages() {
        withContext(Dispatchers.IO) {
            val networkImages = ApodNetworkApi.networkImages.getApodImages(startDate)
            database.apodDao.insertAll(networkImages.asEntity())
        }
    }
}