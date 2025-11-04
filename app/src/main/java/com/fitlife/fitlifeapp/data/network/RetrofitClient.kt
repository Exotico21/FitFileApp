package com.fitlife.fitlifeapp.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Objeto Singleton para crear y gestionar la instancia de Retrofit.
 */
object RetrofitClient {

    // IMPORTANTE: Reemplaza esta URL con la URL base de tu API real.
    // Si la estás corriendo en tu misma máquina (localhost), usa "http://10.0.2.2:PORT/"
    // El emulador de Android no entiende "localhost" o "127.0.0.1". 10.0.2.2 es el alias.
    private const val BASE_URL = "http://TU_API_AQUI.com/api/"

    // Creación "lazy" de la instancia de Retrofit. Solo se construye la primera vez que se necesita.
    val instance: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) // Usa Gson para convertir JSON
            .build()

        retrofit.create(ApiService::class.java)
    }
}


