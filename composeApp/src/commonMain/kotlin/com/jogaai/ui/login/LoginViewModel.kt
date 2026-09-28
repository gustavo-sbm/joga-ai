package com.jogaai.ui.login

import com.jogaai.data.exception.AppException
import com.jogaai.validation.LoginValidator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.jogaai.data.repository.AuthRepository
import com.jogaai.ui.shared.BaseViewModel
import com.jogaai.validation.RegisterValidator


class LoginViewModel(
    val repository: AuthRepository
) : BaseViewModel<LoginUiState>(LoginUiState()) {

    private val validator = LoginValidator()


    fun updateEmail(novoEmail: String){
        _uiState.value = _uiState.value.copy(email = novoEmail, emailError = null)

    }

    fun updateSenha(novaSenha: String){
        _uiState.value = _uiState.value.copy(senha = novaSenha, senhaError = null)

    }

    fun validateEmailField(){
        _uiState.value = _uiState.value.copy(
            emailError = validator.validateEmail(_uiState.value.email)
        )
    }

    fun validateSenhaField(){
        _uiState.value = _uiState.value.copy(
            senhaError = validator.validateSenha(_uiState.value.senha)
        )
    }

    fun login(onSuccess: () -> Unit) {

        validateEmailField()
        validateSenhaField()

        val state = _uiState.value
        if (state.emailError != null || state.senhaError != null) return

        runAction(mensagemPadrao = "Usuario ou senha inválidos"){
            repository.login(email = _uiState.value.email, password = _uiState.value.senha)
            onSuccess()
        }

    }

    override fun comCarregando(
        estado: LoginUiState,
        carregando: Boolean
    ): LoginUiState {
        return estado.copy(isLoading = carregando)
    }

    override fun comErro(
        estado: LoginUiState,
        mensagem: String
    ): LoginUiState {
        return estado.copy(errorMessage = mensagem)
    }

}