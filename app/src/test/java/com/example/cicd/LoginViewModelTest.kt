package com.example.cicd

import org.junit.Test
import org.junit.Assert.*

class LoginViewModelTest {

    @Test
    fun `username and password validation works independently`() {
        val viewModel = LoginViewModel()

        viewModel.onUsernameChange("usr")
        assertFalse(viewModel.isFormValid.value)
        assertEquals("아이디는 최소 4자 이상이어야 합니다.", viewModel.usernameError.value)
        assertNull(viewModel.passwordError.value)

        viewModel.onUsernameChange("user")
        viewModel.onPasswordChange("pass")
        assertFalse(viewModel.isFormValid.value)
        assertNull(viewModel.usernameError.value)
        assertEquals("비밀번호는 최소 6자 이상이어야 합니다.", viewModel.passwordError.value)

        viewModel.onPasswordChange("password")
        assertTrue(viewModel.isFormValid.value)
    }

    @Test
    fun `login with correct credentials`() {
        val viewModel = LoginViewModel()

        viewModel.onUsernameChange("admin")
        viewModel.onPasswordChange("1234")
        viewModel.onLogin()

        assertNull(viewModel.generalError.value)
    }

    @Test
    fun `login with incorrect credentials`() {
        val viewModel = LoginViewModel()

        viewModel.onUsernameChange("wrong")
        viewModel.onPasswordChange("wrongpass")
        viewModel.onLogin()

        assertEquals("아이디 또는 비밀번호가 올바르지 않습니다.", viewModel.generalError.value)
    }
}
