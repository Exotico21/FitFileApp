package com.fitlife.fitlifeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.fitlife.fitlifeapp.navigation.AppNavigation // Importa tu sistema de navegación
import com.fitlife.fitlifeapp.ui.theme.FitLifeAppTheme // Importa el tema de tu app

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // 1. FitLifeAppTheme: Es crucial envolver todo en tu tema.
            //    La falta de un tema es una de las causas más comunes de crashes al inicio.
            FitLifeAppTheme {
                // 2. Surface: Un contenedor que usa los colores del tema.
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // 3. AppNavigation: El punto de entrada a la UI de tu aplicación.
                    AppNavigation()
                }
            }
        }
    }
}
