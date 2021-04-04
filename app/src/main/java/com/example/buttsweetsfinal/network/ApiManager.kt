package com.example.buttsweetsfinal.network

import android.content.Context
import okhttp3.Credentials
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiManager {
    private lateinit var buttSweetsServices: APIService
    private const val BASE_URL = "https://buttsweets.com/wp-json/wc/v2/"

    private val cred = Credentials.basic(
        "ck_24b2d01f8b8651b771fe345754a22f28674b7403",
        "cs_c1819d65feee1457e4fa2bad567ee38d03ad0b16"
    )

    fun initialize() {
        val interceptor = HttpLoggingInterceptor()
        interceptor.apply { interceptor.level = HttpLoggingInterceptor.Level.BODY }
        buttSweetsServices = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpClient.Builder().addInterceptor { chain ->
                val request =
                    chain.request().newBuilder().addHeader("Authorization", cred).build()
                chain.proceed(request)
            }.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIService::class.java)
    }

    fun getProducts(context: Context, apiCallbacks: ApiCallbacks, categoryId: String) {
        ApiExecutor<List<Product>>().addCallToQueue(
            context,
            buttSweetsServices.getProducts(categoryId),
            apiCallbacks
        )
    }
}
