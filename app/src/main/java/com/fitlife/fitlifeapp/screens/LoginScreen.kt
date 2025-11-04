package com.fitlife.fitlifeapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fitlife.fitlifeapp.viewmodel.LoginUiState
import com.fitlife.fitlifeapp.viewmodel.LoginViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    onNavigateToDashboard: () -> Unit,
    loginViewModel: LoginViewModel = viewModel() // Inyecta el ViewModel
) {
    val uiState = loginViewModel.loginUiState
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->
        Box(
            modifier = Modifier.fillMaxSize().padding(padding),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "FitLife",
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier.padding(bottom = 48.dp)
                )

                OutlinedTextField(
                    value = loginViewModel.email,
                    onValueChange = { loginViewModel.email = it },
                    label = { Text("Correo (test@test.com)") },
                    modifier = Modifier.fillMaxWidth(),
                    isError = uiState is LoginUiState.Error,
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = loginViewModel.password,
                    onValueChange = { loginViewModel.password = it },
                    label = { Text("Contraseña (1234)") },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth(),
                    isError = uiState is LoginUiState.Error,
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    onClick = { loginViewModel.onLoginClicked() },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = uiState !is LoginUiState.Loading // Deshabilitado mientras carga
                ) {
                    Text(text = "Iniciar Sesión")
                }
            }

            // Muestra un spinner de carga cuando el estado es Loading
            if (uiState is LoginUiState.Loading) {
                CircularProgressIndicator()
            }
        }
    }

    // Este bloque se ejecuta cuando 'uiState' cambia
    LaunchedEffect(uiState) {
        when (uiState) {
            is LoginUiState.Success -> {
                // Navega al dashboard cuando el login es exitoso
                onNavigateToDashboard()
            }
            is LoginUiState.Error -> {
                // Muestra un mensaje de error en un Snackbar
                snackbarHostState.showSnackbar(
                    message = uiState.message,
                    duration = SnackbarDuration.Short
                )
                // Resetea el estado para que el usuario pueda intentarlo de nuevo
                loginViewModel.resetErrorState()
            }
            else -> { /* No hacer nada en los estados Idle o Loading */ }
        }
    }
}





