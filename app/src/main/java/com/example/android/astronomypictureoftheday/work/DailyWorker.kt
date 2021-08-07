package com.example.android.astronomypictureoftheday.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.android.astronomypictureoftheday.database.getDatabase
import com.example.android.astronomypictureoftheday.repository.ApodRepository
import retrofit2.HttpException
import java.net.HttpRetryException

class DailyWorker(appContext: Context, params: WorkerParameters) :
    CoroutineWorker(appContext, params) {

    companion object {
        const val WORK_NAME = "DailyImageRefreshWorker"
    }

    override suspend fun doWork(): Result {
        val database = getDatabase(applicationContext)
        val repository = ApodRepository(database)
        return try {
            repository.refreshImages()
            Result.success()
        } catch (e: HttpException) {
            Result.retry()
        }
    }

}