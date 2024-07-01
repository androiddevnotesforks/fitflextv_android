package com.dreamsoftware.fitflextv.presentation.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.darkColorScheme

val LocalNavigationProvider = staticCompositionLocalOf<NavHostController> {
    error("No navigation host controller provided.")
}
@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun FitFlexTVTheme(
    content: @Composable () -> Unit,
) {

    val colorScheme = darkColorScheme(
        primary = primary,
        onPrimary = onPrimary,
        primaryContainer = primaryContainer,
        onPrimaryContainer = onPrimaryContainer,
        secondary = secondary,
        onSecondary = onSecondary,
        secondaryContainer = secondaryContainer,
        onSecondaryContainer = onSecondaryContainer,
        tertiary = tertiary,
        onTertiary = onTertiary,
        tertiaryContainer = tertiaryContainer,
        onTertiaryContainer = onTertiaryContainer,
        error = error,
        onError = onError,
        errorContainer = errorContainer,
        onErrorContainer = onErrorContainer,
        background = background,
        onBackground = onBackground,
        surface = surface,
        onSurface = onSurface,
        surfaceVariant = surfaceVariant,
        onSurfaceVariant = onSurfaceVariant,
        border = outline,
        inverseOnSurface = inverseOnSurface,
        inverseSurface = inverseSurface,
        inversePrimary = inversePrimary
    )

    CompositionLocalProvider(
            LocalNavigationProvider provides rememberNavController(),
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content,
            shapes = Shapes
        )
    }
}