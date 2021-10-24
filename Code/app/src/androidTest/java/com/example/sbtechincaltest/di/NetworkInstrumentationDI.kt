package com.example.sbtechincaltest.di

import com.example.sbtechincaltest.network.RetrofitClient
import com.example.sbtechincaltest.network.SBApiService
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Retrofit Koin DI component for Instrumentation Testing
 */
fun configureNetworkForInstrumentationTest(baseTestApi: String) = module {

    single {
        Retrofit.Builder()
            .baseUrl(baseTestApi)
            .addConverterFactory(GsonConverterFactory.create())
            .client(RetrofitClient.client).build()
    }

    factory { get<Retrofit>().create(SBApiService::class.java) }
}
