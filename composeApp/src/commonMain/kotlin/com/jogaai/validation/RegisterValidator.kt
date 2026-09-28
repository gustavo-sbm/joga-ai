package com.jogaai.validation

class RegisterValidator {
    fun validateEmail(email: String): String? {
        return validate(email).notBlank("Informe seu Email!").email("Email inválido").validate()
    }

    fun validateSenha(senha: String): String? {
        return validate(senha).notBlank("Informe sua senha!").minLength(8, "Senha menor que 8 caracteres").validate()
    }

    fun validateName(name: String): String? {
        return validate(name).notBlank("Nome vazio!").name().validate()
    }
}

