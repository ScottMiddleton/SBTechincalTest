package com.example.sbtechincaltest.di

import com.example.sbtechincaltest.datasource.DataRepository
import com.example.sbtechincaltest.datasource.remote.RemoteDataSource
import com.example.sbtechincaltest.network.RetrofitClient
import com.example.sbtechincaltest.network.SBApiService
import com.example.sbtechincaltest.ui.LoginViewModel
import com.example.sbtechincaltest.utils.BASE_URL
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    // Retrofit API Service
    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(RetrofitClient.client).build()
    }

    factory { get<Retrofit>().create(SBApiService::class.java) }

    // Data Sources
    single { RemoteDataSource() }
    single { DataRepository() }

    // ViewModels
    viewModel { LoginViewModel() }
}

