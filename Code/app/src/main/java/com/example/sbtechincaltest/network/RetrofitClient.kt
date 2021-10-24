package com.example.sbtechincaltest.network

import androidx.annotation.Keep
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

@Keep
class RetrofitClient {

    companion object {
        val client: OkHttpClient = OkHttpClient.Builder()
            .readTimeout(5, TimeUnit.SECONDS)
            .writeTimeout(5, TimeUnit.MINUTES)
            .callTimeout(5, TimeUnit.MINUTES)
            .connectTimeout(5, TimeUnit.SECONDS).build()
    }
}