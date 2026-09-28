package com.jogaai.ui.register

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.jogaai.data.AppModule
import com.jogaai.theme.Spacing
import com.jogaai.ui.shared.AppTextField
import com.jogaai.ui.shared.ColumnResponsive
import com.jogaai.ui.shared.responsiveWidth

@Composable
fun RegisterScreen(
    onRegisterSuccess: () -> Unit,
    onNavigateToLogin: () -> Unit,
    viewModel: RegisterViewModel = remember { RegisterViewModel(AppModule.authRepository) },
) {
    val uiState by viewModel.uiState.collectAsState()

    ColumnResponsive {
        Text(
            text = "Cadastre-Se",
            style = MaterialTheme.typography.headlineLarge
        )

        Spacer(modifier = Modifier.height(Spacing.lg))

        Text(
            text = "Crie uma conta",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(Spacing.xxl))

        AppTextField(
            value = uiState.name,
            onValueChange = { novoName ->
                viewModel.updateName(novoName)
            },
            onFocusLost = { viewModel.validateNameField() },
            errorMessage = uiState.nameError,
            modifier = Modifier.responsiveWidth(),
            label = "Nome"
        )


        Spacer(modifier = Modifier.height(Spacing.lg))

        AppTextField(
            value = uiState.email,
            onValueChange = { novoEmail ->
                viewModel.updateEmail(novoEmail)
            },
            onFocusLost = { viewModel.validateEmailField() },
            modifier = Modifier.responsiveWidth(),
            errorMessage = uiState.emailError,
            label = "Email"
        )

        Spacer(modifier = Modifier.height(Spacing.lg))

        AppTextField(
            value = uiState.senha,
            onValueChange = { novaSenha ->
                viewModel.updateSenha(novaSenha)
            },
            onFocusLost = { viewModel.validateSenhaField() },
            modifier = Modifier.responsiveWidth(),
            errorMessage = uiState.senhaError,
            label = "Senha",
            password = true
        )

        Spacer(modifier = Modifier.height(Spacing.xl))

        if (uiState.errorMessage.isNotBlank()) {
            Text(
                text = uiState.errorMessage,
                color = MaterialTheme.colorScheme.error
            )
            Spacer(modifier = Modifier.height(Spacing.xl))
        }

        Button(
            onClick = {
                viewModel.register(onRegisterSuccess)
            },
            modifier = Modifier.responsiveWidth(),
            enabled = !uiState.isLoading
        ) {
            Text(
                text = if (uiState.isLoading) {
                    "Cadastrando..."
                } else {
                    "Cadastrar"
                }
            )
        }

        Spacer(modifier = Modifier.height(Spacing.xl))

        TextButton(onClick = onNavigateToLogin) {
            Text(text = "Entrar na conta")
        }

    }
}
