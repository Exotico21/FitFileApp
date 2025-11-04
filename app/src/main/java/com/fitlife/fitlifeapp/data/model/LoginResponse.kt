package com.fitlife.fitlifeapp.data.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("token") // El nombre debe coincidir con el que devuelve la API
    val token: String,

    @SerializedName("user")
    val user: User // Reutilizamos la clase User que ya teníamos
)


