package com.example.sbtechincaltest.ui.login

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.sbtechincaltest.R
import com.example.sbtechincaltest.base.BaseUITest
import com.example.sbtechincaltest.di.generateTestAppComponent
import com.example.sbtechincaltest.utils.hasTextInputLayoutErrorText
import org.hamcrest.CoreMatchers.not
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import kotlin.test.assertEquals

@RunWith(AndroidJUnit4::class)
class LoginFragmentTest : BaseUITest() {

    private lateinit var navController: NavController
    private lateinit var context: Context

    @Before
    fun beforeAndroidTest() {
        loadKoinModules(generateTestAppComponent(getMockWebServerUrl()).toMutableList())

        val scenario = launchFragmentInContainer<LoginFragment>(Bundle(), R.style.AppTheme)
        navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )

        scenario.onFragment {
            navController.setGraph(R.navigation.nav_graph)
            Navigation.setViewNavController(it.view!!, navController)
            context = it.requireContext()
        }
    }

    @After
    fun afterAndroidTest() {
        unloadKoinModules(generateTestAppComponent(getMockWebServerUrl()).toMutableList())
    }

    @Test
    fun noEmailEntered_submitLogin() {
        onView(withId(R.id.password_et)).perform(typeText("password"), closeSoftKeyboard())
        onView(withId(R.id.login_btn)).perform(click())

        onView(withId(R.id.email_til)).check(
            matches(
                hasTextInputLayoutErrorText(
                    context.getString(
                        R.string.required_field_error
                    )
                )
            )
        )
    }

    @Test
    fun noPasswordEntered_submitLogin() {
        onView(withId(R.id.email_et)).perform(typeText("email@email.com"), closeSoftKeyboard())
        onView(withId(R.id.login_btn)).perform(click())

        onView(withId(R.id.password_til)).check(
            matches(
                hasTextInputLayoutErrorText(
                    context.getString(
                        R.string.required_field_error
                    )
                )
            )
        )
    }

    @Test
    fun noPasswordEntered_logInNotClicked_noError() {
        onView(withId(R.id.password_til)).check(
            matches(
                not(
                    hasTextInputLayoutErrorText(
                        context.getString(
                            R.string.required_field_error
                        )
                    )
                )
            )
        )
    }

    @Test
    fun passwordEntered_thenRemoved_error() {
        onView(withId(R.id.password_et)).perform(typeText("password"))
        onView(withId(R.id.password_et)).perform(clearText(), closeSoftKeyboard())

        onView(withId(R.id.password_til)).check(
            matches(
                hasTextInputLayoutErrorText(
                    context.getString(
                        R.string.required_field_error
                    )
                )
            )
        )
    }

    @Test
    fun emailEntered_thenRemoved_error() {
        onView(withId(R.id.email_et)).perform(typeText("email@email.com"))
        onView(withId(R.id.email_et)).perform(clearText(), closeSoftKeyboard())

        onView(withId(R.id.email_til)).check(
            matches(
                hasTextInputLayoutErrorText(
                    context.getString(
                        R.string.required_field_error
                    )
                )
            )
        )
    }

    @Test
    fun correctCredentialEntered_submitLogin() {
        onView(withId(R.id.email_et)).perform(typeText("email@email.com"))
        onView(withId(R.id.password_et)).perform(typeText("password"), closeSoftKeyboard())

        onView(withId(R.id.login_btn)).perform(click())
        assertEquals(navController.currentDestination?.id, R.id.photosFragment)
    }
}