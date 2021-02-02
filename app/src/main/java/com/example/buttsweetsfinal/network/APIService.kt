package com.example.buttsweetsfinal.network

import com.example.buttsweetsfinal.network.Product
import retrofit2.Call
import retrofit2.http.*

interface APIService {

    @Headers("Content-Type: application/json", "Accept: application/json")
    @GET("products?category=58&per_page=100")
    fun getProducts(
//        @Query("offset") offset: Int,
//        @Query("limit") limit: Int
    ): Call<List<Product>>

}
