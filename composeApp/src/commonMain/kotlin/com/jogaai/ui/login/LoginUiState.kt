package com.jogaai.ui.login

data class LoginUiState(
    val email: String = "",
    val senha: String = "",
    val errorMessage: String = "",
    val emailError: String? = null,
    val senhaError: String? = null,
    val isLoading: Boolean = false
)