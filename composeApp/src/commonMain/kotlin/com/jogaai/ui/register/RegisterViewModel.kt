package com.jogaai.ui.register

import com.jogaai.data.exception.AppException
import com.jogaai.validation.RegisterValidator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.jogaai.data.repository.AuthRepository
import com.jogaai.ui.shared.BaseViewModel

class RegisterViewModel(
    private val repository: AuthRepository
) : BaseViewModel<RegisterUiState> (RegisterUiState()){

    private val validator = RegisterValidator()


    fun updateEmail(email: String) {
        _uiState.value = _uiState.value.copy(email = email, emailError = null)
    }

    fun updateSenha(senha: String) {
        _uiState.value = _uiState.value.copy(senha = senha, senhaError = null)
    }

    fun updateName(name: String) {
        _uiState.value = _uiState.value.copy(name = name, nameError = null)
    }

    fun validateEmailField() {
        _uiState.value = _uiState.value.copy(
            emailError = validator.validateEmail(_uiState.value.email)
        )
    }

    fun validateSenhaField() {
        _uiState.value = _uiState.value.copy(
            senhaError = validator.validateSenha(_uiState.value.senha)
        )
    }

    fun validateNameField() {
        _uiState.value = _uiState.value.copy(
            nameError = validator.validateName(_uiState.value.name)
        )
    }

    fun register(onSuccess: () -> Unit) {

        validateEmailField()
        validateSenhaField()
        validateNameField()

        val state = _uiState.value
        if (state.emailError != null || state.senhaError != null || state.nameError != null) return

        runAction(
            mensagemPadrao = "Usuario ou senha inválidos",
            ) {
            repository.register(
                email = _uiState.value.email,
                password = _uiState.value.senha,
                nome = _uiState.value.name
            )
            onSuccess()
        }

    }

    override fun comCarregando(
        estado: RegisterUiState,
        carregando: Boolean
    ): RegisterUiState {
        return estado.copy(isLoading = carregando)
    }

    override fun comErro(
        estado: RegisterUiState,
        mensagem: String
    ): RegisterUiState {
        return estado.copy(errorMessage = mensagem)
    }
}

