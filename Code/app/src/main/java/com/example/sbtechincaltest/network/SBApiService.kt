package com.example.sbtechincaltest.network

import androidx.annotation.Keep
import com.example.sbtechincaltest.network.responses.PhotoResponse
import retrofit2.Response
import retrofit2.http.GET

@Keep
interface SBApiService {

    @GET("photos")
    suspend fun getPhotos(): Response<List<PhotoResponse>>

}