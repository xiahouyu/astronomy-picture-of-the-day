package com.example.android.astronomypictureoftheday.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.android.astronomypictureoftheday.database.getDatabase
import com.example.android.astronomypictureoftheday.repository.ApodRepository
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val dateabse = getDatabase(application)
    private val imagesRepository = ApodRepository(dateabse)

    init {
        viewModelScope.launch {
            imagesRepository.refreshImages()
        }
    }

    val imageList = imagesRepository.apodImages

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return HomeViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }

}