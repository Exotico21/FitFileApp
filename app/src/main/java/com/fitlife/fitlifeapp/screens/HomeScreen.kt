package com.fitlife.fitlifeapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.fitlife.fitlifeapp.navigation.Routes // <-- 1. IMPORTACIÓN CORRECTA

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) { // Recibe el NavController para poder navegar
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("FitLife Home") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp), // Añadimos un poco de padding general
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "¡Bienvenido a FitLife!",
                style = androidx.compose.material3.MaterialTheme.typography.headlineMedium
            )

            // Ejemplo de un botón que navega a otra pantalla (ej: Perfil)
            Button(
                onClick = {
                    // --- 2. USO CORRECTO DEL OBJETO DE RUTAS ---
                    // Aquí usamos "Routes" en lugar del incorrecto "AppRoutes"
                    navController.navigate(Routes.PROFILE)
                },
                modifier = Modifier.padding(top = 24.dp)
            ) {
                Text(text = "Ir a mi Perfil")
            }

            // Ejemplo de otro botón para ir a las recetas
            Button(
                onClick = {
                    // --- 3. USO CORRECTO DE NUEVO ---
                    navController.navigate(Routes.RECIPES)
                },
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text(text = "Ver Recetas")
            }
        }
    }
}


