package com.example.sbtechincaltest.datasource

import com.example.sbtechincaltest.network.Resource
import com.example.sbtechincaltest.network.responses.PhotoResponse

interface DataRepository {

    suspend fun getPhotos(): Resource<List<PhotoResponse>>

}
