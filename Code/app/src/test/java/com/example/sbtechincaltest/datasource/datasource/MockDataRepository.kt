package com.example.sbtechincaltest.datasource.datasource

import com.example.sbtechincaltest.datasource.DataRepository
import com.example.sbtechincaltest.network.Resource
import com.example.sbtechincaltest.network.responses.PhotoResponse

class MockDataRepository : DataRepository {
    companion object {
        const val testErrorMessage = "Test error message"
    }

    private var shouldReturnNetworkError = false
    private var shouldReturnEmptyList = false

    fun setShouldReturnNetworkError(value: Boolean) {
        shouldReturnNetworkError = value
    }

    fun setReturnEmptyList(value: Boolean) {
        shouldReturnEmptyList = value
    }

    override suspend fun getPhotos(): Resource<List<PhotoResponse>> {
        return if (!shouldReturnNetworkError) {
            if (!shouldReturnEmptyList) {
                return Resource.Success(getMockPhotosList())
            } else {
                Resource.Success(emptyList())
            }
        } else {
            Resource.Error(testErrorMessage)
        }
    }

    fun getMockPhotosList(): MutableList<PhotoResponse> {
        val list = mutableListOf<PhotoResponse>()
        list.add(PhotoResponse("0", "0", "title one", "", "thumbnail_one"))
        list.add(PhotoResponse("0", "0", "title two", "", "thumbnail_two"))
        list.add(PhotoResponse("0", "0", "title three", "", "thumbnail_three"))

        return list
    }
}