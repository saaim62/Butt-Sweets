package com.example.buttsweetsfinal.network

import retrofit2.Call
import retrofit2.http.GET

interface APIService {

//    @Headers("Content-Type: application/json", "Accept: application/json")


    @GET("products?category=58&per_page=100")
//    @GET("buttsweets/cakes")
    fun getCakes(
    ): Call<List<Product>>

    @GET("products?category=57&per_page=100")
    fun getSamosa(
    ): Call<List<Product>>

    @GET("products?category=57&per_page=100")
    fun getTvc(
    ): Call<List<Product>>

    @GET("products?category=90&per_page=100")
    fun getHalwajaat(
    ): Call<List<Product>>

    @GET("products?category=62&per_page=100")
    fun getSweets(
    ): Call<List<Product>>

    @GET("products?category=56&per_page=100")
    fun getInstantBake(
    ): Call<List<Product>>

    @GET("products/{id}/variations/{x}")
    fun getPrice(
    ): Call<List<Product>>

}
