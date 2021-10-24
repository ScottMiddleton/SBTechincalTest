package com.example.sbtechincaltest.datasource.ui.photos

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.sbtechincaltest.datasource.utils.getOrAwaitValue
import com.example.sbtechincaltest.datasource.datasource.MockDataRepository
import com.example.sbtechincaltest.datasource.datasource.MockDataRepository.Companion.testErrorMessage
import com.example.sbtechincaltest.ui.photos.PhotosViewModel
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTest
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@RunWith(AndroidJUnit4::class)
class PhotosFragmentViewModelTest : KoinTest {

    private lateinit var viewModel: PhotosViewModel
    private lateinit var repository: MockDataRepository

    @Before
    fun setup() {
        repository = MockDataRepository()
        viewModel = PhotosViewModel(repository)
    }

    @Test
    fun getPhotos_success_emptyList() {
        repository.setReturnEmptyList(true)
        viewModel.getPhotos()

        val loadingValue = viewModel.photosResponseLoadingLD.getOrAwaitValue()
        assertTrue(loadingValue)

        val value = viewModel.photoResponseSuccessLD.getOrAwaitValue()

        assertTrue(value.isEmpty())
    }

    @Test
    fun getPhotos_success_mockedList() {
        repository.setReturnEmptyList(false)
        viewModel.getPhotos()

        val loadingValue = viewModel.photosResponseLoadingLD.getOrAwaitValue()
        assertTrue(loadingValue)

        val response = viewModel.photoResponseSuccessLD.getOrAwaitValue()

        val mockedList = repository.getMockPhotosList()

        assertEquals(response.size, mockedList.size)

        response.forEachIndexed { index, value ->
            assertEquals(value.thumbnailUrl, mockedList[index].thumbnailUrl )
            assertEquals(value.title, mockedList[index].title )
        }
    }

    @Test
    fun getPhotos_error() {
        repository.setShouldReturnNetworkError(true)
        viewModel.getPhotos()

        val loadingValue = viewModel.photosResponseLoadingLD.getOrAwaitValue()
        assertTrue(loadingValue)

        val value = viewModel.photosResponseErrorLD.getOrAwaitValue()

        assertEquals(value, testErrorMessage)
    }
}