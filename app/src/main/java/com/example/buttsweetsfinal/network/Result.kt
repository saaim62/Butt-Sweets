package com.example.buttsweetsfinal.network

import com.example.buttsweetsfinal.network.Product
import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("products")
    var products: List<Product> = listOf()
)