package com.serpientesescaleras.parcialdos.model
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FruitViewModelFactory(private val repository: FruitRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FruitViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FruitViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}