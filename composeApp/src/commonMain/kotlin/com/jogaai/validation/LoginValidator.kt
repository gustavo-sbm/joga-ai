package com.jogaai.validation


class LoginValidator {
    fun validateEmail(email: String): String? {
        return validate(email).notBlank("Informe seu Email!").email().validate()
    }

    fun validateSenha(senha: String): String? {
        return validate(senha).notBlank().minLength(8).validate()
    }
}
