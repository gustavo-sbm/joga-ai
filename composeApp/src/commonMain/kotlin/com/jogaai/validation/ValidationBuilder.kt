package com.jogaai.validation

class ValidationBuilder(private val value: String) {
    private var error: String? = null

    fun notBlank(message: String = "Erro, valor vazio!") = apply {
        if (error == null && value.isBlank()) error = message
    }

    fun email(message: String = "Email inválido") = apply {
        if (error == null && (!value.contains("@") || !value.contains("."))) error = message
    }

    fun minLength(min: Int, message: String = "Mínimo de $min caracteres") = apply {
        if (error == null && value.length < min) error = message
    }

    fun name(message: String = "Nome inválido") = apply {
        if(error == null && !Regex("^\\p{L}+(?:[ '\\-]\\p{L}+)*$").matches(value)) error = message
    }

    fun validate(): String? = error
}

fun validate(value: String) = ValidationBuilder(value)