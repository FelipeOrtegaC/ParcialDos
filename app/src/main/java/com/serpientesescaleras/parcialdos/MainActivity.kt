package com.serpientesescaleras.parcialdos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import com.serpientesescaleras.parcialdos.model.ApiClient
import com.serpientesescaleras.parcialdos.model.FruitRepository
import com.serpientesescaleras.parcialdos.viewModel.FruitViewModel
import com.serpientesescaleras.parcialdos.viewModel.FruitViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var fruitViewModel: FruitViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val apiService = ApiClient.apiService
        val repository = FruitRepository(apiService)
        val viewModelFactory = FruitViewModelFactory(repository)

        fruitViewModel = ViewModelProvider(this, viewModelFactory)[FruitViewModel::class.java]

        fruitViewModel.fruits.observe(this, { fruits -> })

        fruitViewModel.loadFruits()
    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        val searchView = menu.findItem(R.id.action_search).actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrBlank()) {
                    fruitViewModel.searchFruits(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Puedes realizar acciones mientras el usuario escribe (opcional)
                return true
            }
        })

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_sort_calories -> sortFruitsByNutrient("calories")
            R.id.action_sort_fat -> sortFruitsByNutrient("fat")
            // Agrega más opciones de ordenamiento según sea necesario
        }
        return super.onOptionsItemSelected(item)
    }

    private fun sortFruitsByNutrient(nutrient: String) {
        fruitViewModel.sortFruitsByNutrients(nutrient, true)
    }
}