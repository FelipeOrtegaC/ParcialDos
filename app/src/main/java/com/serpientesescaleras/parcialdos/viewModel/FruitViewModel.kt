package com.serpientesescaleras.parcialdos.viewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.serpientesescaleras.parcialdos.model.Fruit
import com.serpientesescaleras.parcialdos.model.FruitRepository

class FruitViewModel(private val repository: FruitRepository) : ViewModel() {
    private val _fruits = MutableLiveData<List<Fruit>>()
    val fruits: LiveData<List<Fruit>> get() = _fruits

    fun loadFruits() {
        repository.getAllFruits {
            _fruits.postValue(it)
        }
    }

    fun searchFruits(name: String) {
        repository.searchFruits(name) {
            _fruits.postValue(it)
        }
    }

    fun sortFruitsByNutrients(nutrient: String, ascending: Boolean) {
        repository.sortFruitsByNutrients(nutrient, ascending) {
            _fruits.postValue(it)
        }
    }
}

