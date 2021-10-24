package com.example.sbtechincaltest.di

import com.example.sbtechincaltest.datasource.DataRepository
import com.example.sbtechincaltest.datasource.DataRepositoryImpl
import com.example.sbtechincaltest.datasource.remote.RemoteDataSource
import com.example.sbtechincaltest.network.RetrofitClient
import com.example.sbtechincaltest.network.SBApiService
import com.example.sbtechincaltest.ui.login.LoginViewModel
import com.example.sbtechincaltest.ui.photos.PhotosViewModel
import com.example.sbtechincaltest.utils.BASE_URL
import okhttp3.mockwebserver.MockWebServer
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Main Koin DI component for Instrumentation Testing
 */
fun generateTestAppComponent(baseApi: String) = listOf(
    configureNetworkForInstrumentationTest(baseApi),
    depModule
)

val depModule = module {

    // Data Sources
    single { RemoteDataSource() }
    single<DataRepository> { DataRepositoryImpl(get()) }

    // ViewModels
    viewModel { LoginViewModel() }
    viewModel { PhotosViewModel(get()) }

    single { MockWebServer() }
}