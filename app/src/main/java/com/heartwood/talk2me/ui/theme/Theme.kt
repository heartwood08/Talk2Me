package com.heartwood.talk2me.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF6200EE), // Fioletowy
    onPrimary = Color.White, // Biały
    primaryContainer = Color(0xFF121212), // Bardzo ciemny szary
    onPrimaryContainer = Color.White, // Biały
    secondary = Color(0xFF757575), // Szary
    onSecondary = Color.White, // Biały
    secondaryContainer = Color(0xFF212121), // Ciemny szary
    onSecondaryContainer = Color.White, // Biały
    tertiary = Color(0xFFD81B60), // Intensywny różowy
    onTertiary = Color.White, // Biały
    tertiaryContainer = Color(0xFF1A001A), // Ciemny różowy
    onTertiaryContainer = Color.White, // Biały
    error = Color.Red,
    onError = Color.White, // Biały
    background = Color(0xFF121212), // Bardzo ciemny szary
    onBackground = Color.White, // Biały
    surface = Color(0xFF212121), // Ciemny szary
    onSurface = Color.White // Biały
)

// Dla trybu Light
private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF6200EE), // Fioletowy
    onPrimary = Color.White, // Biały
    primaryContainer = Color(0xFFFFFFFF), // Biały
    onPrimaryContainer = Color(0xFF000000), // Czarny
    secondary = Color(0xFF757575), // Szary
    onSecondary = Color.White, // Biały
    secondaryContainer = Color(0xFFE0E0E0), // Jasnoszary
    onSecondaryContainer = Color(0xFF000000), // Czarny
    tertiary = Color(0xFFD81B60), // Intensywny różowy
    onTertiary = Color.White, // Biały
    tertiaryContainer = Color(0xFFFFF3F5), // Bardzo jasny różowy
    onTertiaryContainer = Color(0xFF000000), // Czarny
    error = Color.Red,
    onError = Color.White, // Biały
    background = Color(0xFFEAEAEA), // Jasnoszary
    onBackground = Color(0xFF000000), // Czarny
    surface = Color(0xFFFFFFFF), // Biały
    onSurface = Color(0xFF000000)// Czarny
)
@Composable
fun Talk2MeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
//        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
//            val context = LocalContext.current
//            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
//        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}