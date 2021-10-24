package com.example.sbtechincaltest.ui.photos

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.swipeDown
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.sbtechincaltest.R
import com.example.sbtechincaltest.base.BaseUITest
import com.example.sbtechincaltest.di.generateTestAppComponent
import com.example.sbtechincaltest.utils.atPosition
import com.example.sbtechincaltest.utils.withCustomConstraints
import org.awaitility.Awaitility.await
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import java.net.HttpURLConnection

@RunWith(AndroidJUnit4::class)
class PhotosFragmentTest : BaseUITest() {
    private lateinit var navController: NavController
    private lateinit var viewModel: PhotosViewModel

    @Before
    fun beforeAndroidTest() {
        loadKoinModules(generateTestAppComponent(getMockWebServerUrl()).toMutableList())

        val scenario = launchFragmentInContainer<PhotosFragment>(Bundle(), R.style.AppTheme)
        navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )

        scenario.onFragment {
            navController.setGraph(R.navigation.nav_graph)
            Navigation.setViewNavController(it.view!!, navController)
            viewModel = it.viewModel
        }
    }

    @After
    fun afterAndroidTest() {
        unloadKoinModules(generateTestAppComponent(getMockWebServerUrl()).toMutableList())
    }


    @Test
    fun response_success_displaysItemInList() {
        mockNetworkResponseWithFileContent("photos_success.json", HttpURLConnection.HTTP_OK)

        await().until { viewModel.photoResponseSuccessLD.value?.isNotEmpty() }

        onView(withId(R.id.photos_rv))
            .check(
                matches(
                    atPosition(
                        0,
                        hasDescendant(withText("accusamus beatae ad facilis cum similique qui sunt"))
                    )
                )
            )
    }

    @Test
    fun response_error_showEmptyTv() {
        mockNetworkResponse(404)

        await().until { viewModel.photosResponseErrorLD.value?.isNotEmpty() }

        onView(withId(R.id.empty_list_tv))
            .check(matches(isDisplayed()))
    }

    @Test
    fun response_error_swipeToRefresh_success() {
        mockNetworkResponse(404)

        await().until { viewModel.photosResponseErrorLD.value?.isNotEmpty() }

        onView(withId(R.id.empty_list_tv))
            .check(matches(isDisplayed()))

        mockNetworkResponseWithFileContent("photos_success.json", HttpURLConnection.HTTP_OK)

        onView(withId(R.id.refresh_layout))
            .perform(withCustomConstraints(swipeDown(), isDisplayingAtLeast(85)))

        await().until { viewModel.photoResponseSuccessLD.value?.isNotEmpty() }

        onView(withId(R.id.photos_rv))
            .check(
                matches(
                    atPosition(
                        0,
                        hasDescendant(withText("accusamus beatae ad facilis cum similique qui sunt"))
                    )
                )
            )
    }
}