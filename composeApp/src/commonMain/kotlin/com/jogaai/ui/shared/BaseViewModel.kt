package com.jogaai.ui.shared

import com.jogaai.data.exception.AppException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<S>(estadoInicial: S) {
    protected val _uiState = MutableStateFlow(estadoInicial)
    val uiState: StateFlow<S> = _uiState

    private val scope = CoroutineScope(Dispatchers.Main)

    protected abstract fun comCarregando(estado: S, carregando: Boolean): S
    protected abstract fun comErro(estado: S, mensagem: String): S

    protected fun runAction(mensagemPadrao: String, action: suspend () -> Unit) {
        scope.launch {
            _uiState.value = comErro(comCarregando(_uiState.value, true), "")
            try {
                action()
            } catch (e: AppException) {
                _uiState.value = comErro(_uiState.value, e.message)
            } catch (e: Exception) {
                _uiState.value = comErro(_uiState.value, mensagemPadrao)
            } finally {
                _uiState.value = comCarregando(_uiState.value, false)
            }
        }
    }
}