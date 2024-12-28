package com.example.cicd

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LoginViewModel : ViewModel() {

    private val _username = MutableStateFlow("")
    val username: StateFlow<String> = _username

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    private val _usernameError = MutableStateFlow<String?>(null)
    val usernameError: StateFlow<String?> = _usernameError

    private val _passwordError = MutableStateFlow<String?>(null)
    val passwordError: StateFlow<String?> = _passwordError

    private val _isFormValid = MutableStateFlow(false)
    val isFormValid: StateFlow<Boolean> = _isFormValid

    private val _generalError = MutableStateFlow<String?>(null)
    val generalError: StateFlow<String?> = _generalError

    fun onUsernameChange(newUsername: String) {
        _username.value = newUsername
        validateUsername()
        validateForm()
        clearGeneralError()
    }

    fun onPasswordChange(newPassword: String) {
        _password.value = newPassword
        validatePassword()
        validateForm()
        clearGeneralError()
    }

    fun onLogin() {
        if (_username.value == "admin" && _password.value == "123456") {
            _generalError.value = null
        } else {
            _generalError.value = "아이디 또는 비밀번호가 올바르지 않습니다."
        }
    }

    private fun validateUsername() {
        _usernameError.value = if (_username.value.length >= 4) null else "아이디는 최소 4자 이상이어야 합니다."
    }

    private fun validatePassword() {
        _passwordError.value = if (_password.value.length >= 6) null else "비밀번호는 최소 6자 이상이어야 합니다."
    }

    private fun validateForm() {
        _isFormValid.value =
            !_username.value.isBlank() && !_password.value.isBlank() && _usernameError.value == null && _passwordError.value == null
    }

    private fun clearGeneralError() {
        _generalError.value = null
    }
}
