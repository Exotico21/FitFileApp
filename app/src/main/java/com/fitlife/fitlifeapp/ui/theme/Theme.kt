package com.fitlife.fitlifeapp.ui.theme

// --- IMPORTACIONES CORREGIDAS ---
// Ahora apuntan a tu propio paquete 'com.fitlife.fitlifeapp'
import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

/**
 * Esquema de Color Oscuro (Dark Mode)
 */
private val DarkColorScheme = darkColorScheme(
    primary = PrimaryBlue,
    secondary = SecondaryGreen,
    tertiary = AccentOrange,
    error = ErrorRed,
    background = DarkGray,
    onBackground = LightGray,
    surface = DarkGray,
    onSurface = LightGray
)

/**
 * Esquema de Color Claro (Light Mode)
 */
private val LightColorScheme = lightColorScheme(
    primary = PrimaryBlue,
    secondary = SecondaryGreen,
    tertiary = AccentOrange,
    error = ErrorRed,
    background = LightGray,
    onBackground = DarkGray,
    surface = Color.White,
    onSurface = DarkGray
)

@Composable
fun FitLifeAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography, // Asegúrate de que tienes un archivo Typography.kt en la misma carpeta
        content = content
    )
}

