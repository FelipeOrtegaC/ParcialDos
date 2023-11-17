package com.serpientesescaleras.parcialdos.model
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("fruit/all")
    fun getAllFruits(): Call<List<Fruit>>

    @GET("fruit")
    fun searchFruits(@Query("name") name: String): Call<List<Fruit>>

}