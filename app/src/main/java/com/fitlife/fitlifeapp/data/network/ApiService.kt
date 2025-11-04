package com.fitlife.fitlifeapp.data.network

import com.fitlife.fitlifeapp.data.model.LoginRequest
import com.fitlife.fitlifeapp.data.model.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Interfaz que define todos los endpoints de la API de FitLife.
 * Retrofit usará esta interfaz para generar las llamadas de red.
 */
interface ApiService {

    /**
     * Endpoint para iniciar sesión.
     * @param loginRequest El cuerpo de la petición con email y contraseña.
     * @return Una respuesta que contiene el token y los datos del usuario.
     */
    @POST("auth/login") // La parte de la URL que corresponde a este endpoint
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

    // --- Aquí añadirás los otros endpoints en el futuro ---
    // @GET("me")
    // suspend fun getProfile(): Response<User>
    //
    // @POST("progress")
    // suspend fun postProgress(@Body progress: ProgressRequest): Response<Unit>
}


