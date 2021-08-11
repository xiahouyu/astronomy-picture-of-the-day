package com.example.android.astronomypictureoftheday.viewModel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.lifecycle.*
import com.example.android.astronomypictureoftheday.database.getDatabase
import com.example.android.astronomypictureoftheday.model.ApodImage
import com.example.android.astronomypictureoftheday.repository.ApodRepository
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val database = getDatabase(application)
    private val imagesRepository = ApodRepository(database)

    init {
        val cm = application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnected == true

        if (isConnected) {
            viewModelScope.launch {
                imagesRepository.refreshImages()
            }
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

    private val _navigateToSelectedCard = MutableLiveData<String>()

    // The external immutable LiveData for the navigation property
    val navigateToSelectedCard: LiveData<String>
        get() = _navigateToSelectedCard

    fun displayCardDetails(dateString: String) {
        _navigateToSelectedCard.value = dateString
    }

    fun displayCardDetailsComplete() {
        _navigateToSelectedCard.value = null
    }

}