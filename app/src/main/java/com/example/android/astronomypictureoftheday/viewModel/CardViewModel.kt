package com.example.android.astronomypictureoftheday.viewModel

import android.app.Application
import androidx.lifecycle.*
import com.example.android.astronomypictureoftheday.model.ApodImage

class CardViewModel(apodImage: ApodImage, app: Application) : AndroidViewModel(app) {

    private val _selectedCard = MutableLiveData<ApodImage>()
    val selectedCard: LiveData<ApodImage>
        get() = _selectedCard

    init {
        _selectedCard.value = apodImage
    }

    class Factory(
        private val apodImage: ApodImage,
        private val application: Application
    ) : ViewModelProvider.Factory {
        @Suppress("unchecked_cast")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CardViewModel::class.java)) {
                return CardViewModel(apodImage, application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

}