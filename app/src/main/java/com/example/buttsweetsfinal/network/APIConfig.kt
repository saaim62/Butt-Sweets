package com.example.buttsweetsfinal.network

import android.content.Context
import com.google.gson.GsonBuilder
import okhttp3.Credentials
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory


object APIConfig {

    val BASE_URL = "https://buttsweets.com/wp-json/wc/v2/"

    private var retrofit: Retrofit? = null
    val cred = Credentials.basic(
        "ck_24b2d01f8b8651b771fe345754a22f28674b7403",
        "cs_c1819d65feee1457e4fa2bad567ee38d03ad0b16"
    )

    var gson = GsonBuilder()
        .setLenient()
        .create()

    fun getRetrofitClient(context: Context): Retrofit {

        //    File httpCacheDirectory = new File(context.getCacheDir(), "httpCache");
        //    Cache cache = new Cache(httpCacheDirectory, 70 * 1024 * 1024);

        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OkHttpClient.Builder().addInterceptor { chain ->
                    val request =
                        chain.request().newBuilder().addHeader("Authorization", cred).build()
                    chain.proceed(request)
                }.build())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }
        return retrofit!!
    }
}
