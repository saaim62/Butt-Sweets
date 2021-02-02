package com.example.buttsweetsfinal.network

import com.example.buttsweetsfinal.network.Product
import retrofit2.Call
import retrofit2.http.*

interface APIService {

    @Headers("Content-Type: application/json", "Accept: application/json")
    @GET("products?category=58&per_page=100")
    fun getCakes(
    ): Call<List<Product>>

    @Headers("Content-Type: application/json", "Accept: application/json")
    @GET("products?category=57&per_page=100")
    fun getSamosa(
    ): Call<List<Product>>

    @Headers("Content-Type: application/json", "Accept: application/json")
    @GET("products?category=57&per_page=100")
    fun getTvc(
    ): Call<List<Product>>

    @Headers("Content-Type: application/json", "Accept: application/json")
    @GET("products?category=90&per_page=100")
    fun getHalwajaat(
    ): Call<List<Product>>

    @Headers("Content-Type: application/json", "Accept: application/json")
    @GET("products?category=62&per_page=100")
    fun getSweets(
    ): Call<List<Product>>

    @Headers("Content-Type: application/json", "Accept: application/json")
    @GET("products?category=56&per_page=100")
    fun getInstantBake(
    ): Call<List<Product>>
}
