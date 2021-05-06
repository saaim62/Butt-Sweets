package com.example.buttsweetsfinal.modules

import android.content.Context
import com.example.buttsweetsfinal.network.ApiInterface
import com.example.buttsweetsfinal.network.ApiManager
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {

    fun getApiInterface(retrofit: Retrofit): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }

    fun getAPIManger(apiInterface: ApiInterface, context: Context): ApiManager {
        return ApiManager(apiInterface, context)
    }

    single { getApiInterface(get()) }

    single { getAPIManger(get(), get())}
}