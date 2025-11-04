package com.fitlife.fitlifeapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fitlife.fitlifeapp.data.AuthRepository
import kotlinx.coroutines.launch

// Define los posibles estados de la UI para la pantalla de Login
sealed class LoginUiState {
    object Idle : LoginUiState() // Reposo, estado inicial
    object Loading : LoginUiState() // Cargando, esperando respuesta de la API
    data class Success(val token: String) : LoginUiState() // Éxito en el login
    data class Error(val message: String) : LoginUiState() // Error en el login
}

class LoginViewModel : ViewModel() {

    // El estado de la UI que será observado por el Composable
    var loginUiState: LoginUiState by mutableStateOf(LoginUiState.Idle)
        private set

    // Variables para los campos del formulario, observadas por el Composable
    var email by mutableStateOf("")
    var password by mutableStateOf("")

    // Instancia del repositorio (usando la implementación falsa)
    private val authRepository = AuthRepository()

    // Función que se llama desde la UI para iniciar el proceso de login
    fun onLoginClicked() {
        // Validación básica de campos
        if (email.isBlank() || password.isBlank()) {
            loginUiState = LoginUiState.Error("El correo y la contraseña no pueden estar vacíos.")
            return
        }

        viewModelScope.launch {
            // 1. Poner la UI en estado de carga
            loginUiState = LoginUiState.Loading

            // 2. Llamar al repositorio (que simula la llamada a la API)
            val result = authRepository.login(email, password)

            // 3. Procesar el resultado
            result.fold(
                onSuccess = { token ->
                    // Éxito: actualizamos el estado con el token
                    loginUiState = LoginUiState.Success(token)
                },
                onFailure = { error ->
                    // Error: actualizamos el estado con el mensaje
                    loginUiState = LoginUiState.Error(error.message ?: "Error desconocido")
                }
            )
        }
    }

    // Permite resetear el estado de la UI desde fuera, por ejemplo, después de mostrar un error
    fun resetErrorState() {
        loginUiState = LoginUiState.Idle
    }
}
