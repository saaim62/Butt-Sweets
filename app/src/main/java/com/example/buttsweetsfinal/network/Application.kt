package com.example.buttsweetsfinal.network

import android.app.Application

class Application : Application() {

    override fun onCreate() {
        super.onCreate()
        ApiManager.initialize()
    }
}