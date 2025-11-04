package com.fitlife.fitlifeapp.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fitlife.fitlifeapp.screens.LoginScreen // Asegúrate que la importación es correcta

/**
 * Define las rutas de navegación de la aplicación y gestiona el flujo entre pantallas.
 * Usamos un nombre único y consistente para evitar errores de referencia.
 */
object Routes {
    // Rutas de las pantallas principales (usadas en NavHost)
    const val LOGIN = "login"
    const val DASHBOARD = "dashboard"
    const val RECIPES = "recipes"
    const val PROFILE = "profile"
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    // NavHost es el contenedor de la navegación, inicia en el Login
    NavHost(
        navController = navController,
        startDestination = Routes.LOGIN
    ) {
        // 1. Pantalla de Login
        composable(Routes.LOGIN) {
            LoginScreen(
                // Al presionar "Iniciar Sesión", navegamos al dashboard
                onNavigateToDashboard = {
                    navController.navigate(Routes.DASHBOARD) {
                        // --- SINTAXIS CORREGIDA Y MEJORADA ---
                        // Limpiamos la pila de navegación hasta el inicio para que no se
                        // pueda regresar al Login con el botón "Atrás".
                        popUpTo(navController.graph.findStartDestination().id) {
                            inclusive = true
                        }
                        // Evita que se creen múltiples instancias de la misma pantalla.
                        launchSingleTop = true
                    }
                },

            )
        }

        // 2. Pantalla de Dashboard (Placeholder temporal)
        composable(Routes.DASHBOARD) {
            // Este es un marcador de posición. Aquí deberías poner tu pantalla de Dashboard real.
            // Por ejemplo: DashboardScreen()
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                Text(
                    text = "¡Dashboard - Logueado con éxito! Ahora vamos por la API.",
                    modifier = Modifier.padding(24.dp),
                    style = MaterialTheme.typography.headlineMedium
                )
            }
        }

        // Aquí se añadirán más composables (RECIPES, PROFILE, etc.) en el futuro
        // Ejemplo de cómo añadirías la pantalla de recetas:
        /*
        composable(Routes.RECIPES) {
            RecipesScreen(navController = navController)
        }
        */

        // Ejemplo de cómo añadirías la pantalla de perfil:
        /*
        composable(Routes.PROFILE) {
            ProfileScreen(navController = navController)
        }
        */
    }
}


