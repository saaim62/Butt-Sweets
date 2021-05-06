package com.example.buttsweetsfinal.modules

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Credentials
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val retrofitModule = module {
    val BASE_URL = "https://buttsweets.com/wp-json/wc/v2/"
    val cred = Credentials.basic(
        "ck_24b2d01f8b8651b771fe345754a22f28674b7403",
        "cs_c1819d65feee1457e4fa2bad567ee38d03ad0b16"
    )

    fun getGson(): Gson {
        return GsonBuilder().setLenient().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()
    }

    fun getHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return OkHttpClient.Builder().addInterceptor(logging).addInterceptor { chain ->
            val request =
                chain.request().newBuilder().addHeader("Authorization", cred).build()
            chain.proceed(request)
        }.connectTimeout(20, TimeUnit.SECONDS).readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.MINUTES).build()
    }

    fun getRetrofit(
        factory: Gson,
        client: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(factory))
            .client(client)
            .build()
    }
    single { getGson() }
    single { getHttpClient() }
    single { getRetrofit(get(), get()) }
}