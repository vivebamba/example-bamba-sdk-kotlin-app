package com.example.bambainsidekotlin

import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.vivebamba.client.infrastructure.ApiClient

class App : MultiDexApplication() {

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)

    }

    override fun onCreate() {
        super.onCreate()
        ApiClient.apiKey["x-api-key"] = getString(R.string.bamba_api_key)
    }
}