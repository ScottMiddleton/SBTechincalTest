package com.example.sbtechincaltest.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    var email = ""
    var password = ""

    private val _emailValidLD = MutableLiveData<Boolean>()
    val emailValidLD: LiveData<Boolean>
        get() = _emailValidLD

    private val _passwordValidLD = MutableLiveData<Boolean>()
    val passwordValidLD: LiveData<Boolean>
        get() = _passwordValidLD

    private val _loginSuccessLD = MutableLiveData<Boolean>()
    val loginSuccessLD: LiveData<Boolean>
        get() = _loginSuccessLD

    fun validateEmail() {
        val isValid = email.isNotEmpty()
        _emailValidLD.value = isValid
    }

    fun validatePassword() {
        val isValid = password.isNotEmpty()
        _passwordValidLD.value = isValid
    }

    fun submitLogin() {
        validateEmail()
        validatePassword()
        _loginSuccessLD.value = _emailValidLD.value == true && _passwordValidLD.value == true
    }
}