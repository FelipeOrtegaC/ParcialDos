package com.serpientesescaleras.parcialdos.model
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FruitRepository(apiService: ApiService) {
    private val apiService: ApiService = ApiClient.apiService

    fun getAllFruits(callback: (List<Fruit>) -> Unit) {
        apiService.getAllFruits().enqueue(object : Callback<List<Fruit>> {
            override fun onResponse(call: Call<List<Fruit>>, response: Response<List<Fruit>>) {
                if (response.isSuccessful) {
                    callback(response.body() ?: emptyList())
                }
            }

            override fun onFailure(call: Call<List<Fruit>>, t: Throwable) {
                // Manejar el error
                    }
             })
        }

    fun searchFruits(name: String, callback: (List<Fruit>) -> Unit) {
        apiService.searchFruits(name).enqueue(object : Callback<List<Fruit>> {
            override fun onResponse(call: Call<List<Fruit>>, response: Response<List<Fruit>>) {
                if (response.isSuccessful) {
                    callback(response.body() ?: emptyList())
                }
            }

            override fun onFailure(call: Call<List<Fruit>>, t: Throwable) {
                // Manejar el error
            }
        })
    }

    // Método para ordenar frutas por nutrientes
    fun sortFruitsByNutrients(nutrient: String, ascending: Boolean, callback: (List<Fruit>) -> Unit) {
        apiService.getAllFruits().enqueue(object : Callback<List<Fruit>> {
            override fun onResponse(call: Call<List<Fruit>>, response: Response<List<Fruit>>) {
                if (response.isSuccessful) {
                    val sortedList = response.body()?.sortedBy {
                        when (nutrient) {
                            "calories" -> it.calories
                            "fat" -> it.fat
                            "sugar" -> it.sugar
                            "carbohydrates" -> it.carbohydrates
                            "protein" -> it.protein
                            else -> 0.0
                        }
                    }
                    val result = if (ascending) sortedList else sortedList?.reversed()
                    callback(result ?: emptyList())
                }
            }

            override fun onFailure(call: Call<List<Fruit>>, t: Throwable) {
                // Manejar el error
            }
        })
    }
}
