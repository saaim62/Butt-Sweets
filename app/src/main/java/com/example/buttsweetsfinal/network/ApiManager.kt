package com.example.buttsweetsfinal.network

import android.content.Context
import okhttp3.Credentials
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiManager(var apiInterface: ApiInterface, private val context: Context) {

    fun getProducts(apiCallbacks: ApiCallbacks, categoryId: String) {
        ApiExecutor<List<Product>>().addCallToQueue(
            context,
            apiInterface.getProducts(categoryId),
            apiCallbacks
        )
    }
}
