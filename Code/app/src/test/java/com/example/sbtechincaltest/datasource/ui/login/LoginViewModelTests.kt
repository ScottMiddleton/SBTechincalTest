package com.example.sbtechincaltest.datasource.ui.login

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.sbtechincaltest.ui.login.LoginViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTest

@RunWith(AndroidJUnit4::class)
class LoginViewModelTests : KoinTest {

    private lateinit var viewModel: LoginViewModel

    @Before
    fun setUp() {
        viewModel = LoginViewModel()
    }

    @Test
    fun validateEmail_EmptyEmail() {
        viewModel.email = ""
        viewModel.validateEmail()

        assertEquals(viewModel.emailValidLD.value, false)
    }

    @Test
    fun validateEmail_validEmail() {
        viewModel.email = "email@email.com"
        viewModel.validateEmail()

        assertEquals(viewModel.emailValidLD.value, true)
    }

    @Test
    fun validatePassword_EmptyPassword() {
        viewModel.password = ""
        viewModel.validatePassword()

        assertEquals(viewModel.passwordValidLD.value, false)
    }

    @Test
    fun validatePassword_validPassword() {
        viewModel.password = "password"
        viewModel.validatePassword()

        assertEquals(viewModel.passwordValidLD.value, true)
    }

    @Test
    fun submitLogin_emptyPasswordAndEmail_LoginFailure() {
        viewModel.password = ""
        viewModel.email = ""
        viewModel.submitLogin()

        assertEquals(viewModel.emailValidLD.value, false)
        assertEquals(viewModel.passwordValidLD.value, false)
        assertEquals(viewModel.loginSuccessLD.value, false)
    }

    @Test
    fun submitLogin_emptyPasswordAndValidEmail_LoginFailure() {
        viewModel.password = ""
        viewModel.email = "email@email.com"
        viewModel.submitLogin()

        assertEquals(viewModel.emailValidLD.value, true)
        assertEquals(viewModel.passwordValidLD.value, false)
        assertEquals(viewModel.loginSuccessLD.value, false)
    }

    @Test
    fun submitLogin_validPasswordAndEmptyEmail_LoginFailure() {
        viewModel.password = "password"
        viewModel.email = ""
        viewModel.submitLogin()

        assertEquals(viewModel.emailValidLD.value, false)
        assertEquals(viewModel.passwordValidLD.value, true)
        assertEquals(viewModel.loginSuccessLD.value, false)
    }

    @Test
    fun submitLogin_validPasswordAndValidEmail_LoginSuccess() {
        viewModel.password = "password"
        viewModel.email = "email@email.com"
        viewModel.submitLogin()

        assertEquals(viewModel.emailValidLD.value, true)
        assertEquals(viewModel.passwordValidLD.value, true)
        assertEquals(viewModel.loginSuccessLD.value, true)
    }
}