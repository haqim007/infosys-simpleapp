package dev.haqim.simpleapp.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

// Dark Theme Colors
private val DarkColorScheme = darkColorScheme(
    primary = PrimaryColor,
    secondary = SecondaryColor,
    tertiary = Color(0xFF3D3D3D), // Dark gray
    background = Color(0xFF121212),
    surface = Color(0xFF1E1E1E),
    onPrimary = OnPrimaryColor,
    onSecondary = OnSecondaryColor,
    onTertiary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White
)

// Light Theme Colors
private val LightColorScheme = lightColorScheme(
    primary = PrimaryColor,  // Orange
    secondary = SecondaryColor,  // Dark gray
    tertiary = Color(0xFFD9A7A7),  // Soft pinkish tone for profile image
    background = BackgroundColor,  // Light gray background
    surface = SurfaceColor,  // White for cards, dialogs, etc.
    onPrimary = OnPrimaryColor,  // White text on orange
    onSecondary = OnSecondaryColor,  // White text on secondary elements
    onTertiary = Color.Black,  // Dark text on tertiary
    onBackground = OnBackgroundColor,  // Black text on background
    onSurface = OnSurfaceColor  // Black text on surfaces
)

@Composable
fun SimpleAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}