package com.jogaai.ui.register

data class RegisterUiState (
    val name: String = "",
    val email: String = "",
    val senha: String = "",
    val nameError: String? = null,
    val emailError: String? = null,
    val senhaError: String? = null,
    val errorMessage: String = "",
    val isLoading: Boolean = false
)