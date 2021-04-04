package com.example.buttsweetsfinal.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    //    @GET("buttsweets/cakes")
    @GET("products?per_page=100")
    fun getProducts(
        @Query("category") query: String
    ): Call<List<Product>>

    @GET("products/{id}/variations/{x}")
    fun getPrice(): Call<List<Product>>
}
