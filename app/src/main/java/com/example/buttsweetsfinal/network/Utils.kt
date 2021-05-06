package com.example.buttsweetsfinal.network

import android.content.Context
import android.net.ConnectivityManager
import android.util.Patterns
import java.util.*

object Utils {

    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}