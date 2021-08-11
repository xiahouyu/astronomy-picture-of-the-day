package com.example.android.astronomypictureoftheday.viewModel

import android.app.Application
import androidx.lifecycle.*
import com.example.android.astronomypictureoftheday.database.getDatabase
import com.example.android.astronomypictureoftheday.model.ApodImage
import com.example.android.astronomypictureoftheday.repository.ApodRepository
import kotlinx.coroutines.launch

class CardViewModel(apodImageDate: String, app: Application) : AndroidViewModel(app) {

    private val database = getDatabase(app)
    private val repository = ApodRepository(database, apodImageDate)

    private lateinit var selectedCard: LiveData<ApodImage>

    fun getSelectedCard() = selectedCard

    init {
        viewModelScope.launch {
            selectedCard = repository.apodImageByDate
        }
    }

    class Factory(
        private val apodImageDate: String,
        private val application: Application
    ) : ViewModelProvider.Factory {
        @Suppress("unchecked_cast")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CardViewModel::class.java)) {
                return CardViewModel(apodImageDate, application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

}