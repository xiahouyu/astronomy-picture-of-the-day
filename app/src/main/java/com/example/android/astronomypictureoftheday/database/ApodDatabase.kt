package com.example.android.astronomypictureoftheday.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [ApodEntity::class], version = 1)
abstract class ApodDatabase : RoomDatabase() {
    abstract val apodDao: ApodDao
}

private lateinit var INSTANCE: ApodDatabase

fun getDatabase(context: Context): ApodDatabase {
    synchronized(ApodDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                ApodDatabase::class.java,
                "astronomy_picture_archive").build()
        }
    }
    return INSTANCE
}
