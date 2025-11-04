package com.fitlife.fitlifeapp.data.model

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("email") // El nombre debe coincidir con el que espera la API
    val email: String,

    @SerializedName("password") // El nombre debe coincidir con el que espera la API
    val password: String
)


