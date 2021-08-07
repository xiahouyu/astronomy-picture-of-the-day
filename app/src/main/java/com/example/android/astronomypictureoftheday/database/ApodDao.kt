package com.example.android.astronomypictureoftheday.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ApodDao {
    @Query("Select * From daily_astronomy_picture_table")
    fun getImages(): LiveData<List<ApodEntity>>

    @Insert(onConflict= OnConflictStrategy.REPLACE)
    fun insertAll(images: List<ApodEntity>)

    @Query("Select * From daily_astronomy_picture_table Where date = :key")
    fun getByDate(key: String): LiveData<ApodEntity>
}