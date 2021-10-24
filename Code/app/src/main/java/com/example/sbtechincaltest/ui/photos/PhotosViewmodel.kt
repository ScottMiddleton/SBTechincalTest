package com.example.sbtechincaltest.ui.photos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sbtechincaltest.datasource.DataRepository
import com.example.sbtechincaltest.network.Resource
import com.example.sbtechincaltest.network.responses.PhotoResponse
import kotlinx.coroutines.launch

class PhotosViewModel(private val dataRepository: DataRepository) : ViewModel() {

    private val _photosResponseSuccessLD = MutableLiveData<List<PhotoListItem>>()
    val photoResponseSuccessLD: LiveData<List<PhotoListItem>>
        get() = _photosResponseSuccessLD

    private val _photosResponseErrorLD = MutableLiveData<String>()
    val photosResponseErrorLD: LiveData<String>
        get() = _photosResponseErrorLD

    private val _photosResponseLoadingLD = MutableLiveData<Boolean>()
    val photosResponseLoadingLD: LiveData<Boolean>
        get() = _photosResponseLoadingLD

    init {
        getPhotos()
    }

    fun getPhotos() {
        viewModelScope.launch {
            _photosResponseLoadingLD.value = true
            when (val photosResponse = dataRepository.getPhotos()) {
                is Resource.Success -> {
                    _photosResponseSuccessLD.value =
                        convertResponseToListItems(photosResponse.data as List<PhotoResponse>)
                }
                is Resource.Error -> {
                    _photosResponseErrorLD.value = photosResponse.message!!
                }
                is Resource.Loading -> {
                    _photosResponseLoadingLD.value = true
                }
            }
        }
    }

    private fun convertResponseToListItems(photos: List<PhotoResponse>): List<PhotoListItem> {
        val photosListItems = mutableListOf<PhotoListItem>()

        photos.forEach {
            photosListItems.add(PhotoListItem(it.thumbnailUrl, it.title))
        }

        return photosListItems
    }
}