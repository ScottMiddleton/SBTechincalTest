package com.example.sbtechincaltest.datasource

import com.example.sbtechincaltest.datasource.remote.RemoteDataSource
import com.example.sbtechincaltest.network.Resource
import com.example.sbtechincaltest.network.responses.PhotoResponse

class DataRepository(private val remoteDataSource: RemoteDataSource) {

    suspend fun getPhotos(): Resource<List<PhotoResponse>> {
        return remoteDataSource.getPhotos()
    }

}
