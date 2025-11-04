package com.fitlife.fitlifeapp.data

import com.fitlife.fitlifeapp.data.model.LoginRequest
import com.fitlife.fitlifeapp.data.network.RetrofitClient

/**
 * Repositorio que gestiona la autenticación.
 * AHORA usa la implementación REAL con Retrofit.
 */
class AuthRepository {

    private val apiService = RetrofitClient.instance // Obtenemos la instancia de nuestro cliente de red

    // Simula el endpoint /auth/login
    suspend fun login(email: String, pass: String): Result<String> {
        return try {
            val loginRequest = LoginRequest(email, pass)
            val response = apiService.login(loginRequest)

            if (response.isSuccessful) {
                // Éxito (código 2xx)
                val loginResponse = response.body()
                if (loginResponse != null) {
                    // Devolvemos el token
                    Result.success(loginResponse.token)
                } else {
                    Result.failure(Exception("La respuesta de la API está vacía."))
                }
            } else {
                // Error (código 3xx, 4xx, 5xx)
                // Aquí podrías leer el cuerpo del error para un mensaje más específico
                Result.failure(Exception("Error en el login: ${response.code()} ${response.message()}"))
            }
        } catch (e: Exception) {
            // Error de red (sin conexión, timeout, etc.)
            Result.failure(Exception("Error de red: ${e.message}"))
        }
    }
}




