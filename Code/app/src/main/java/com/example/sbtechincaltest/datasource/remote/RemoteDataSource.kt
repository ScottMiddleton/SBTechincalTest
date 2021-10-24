package com.example.sbtechincaltest.datasource.remote

import com.example.sbtechincaltest.network.Resource
import com.example.sbtechincaltest.network.SBApiService
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import retrofit2.Response

class RemoteDataSource: KoinComponent {
    private val sbApiService by inject<SBApiService>()

    private suspend fun <T> getResult(call: suspend () -> Response<T>): Resource<T> {
        return try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                Resource.Success(body!!)
            } else {
                Resource.Error("Network Error")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error("Network Error")
        }
    }
}





