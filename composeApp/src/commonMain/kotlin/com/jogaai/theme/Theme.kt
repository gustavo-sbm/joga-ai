package com.jogaai.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
    primary = Teal400,
    onPrimary = Neutral0,
    primaryContainer = Teal50,
    onPrimaryContainer = Teal900,
    background = NeutralBg,
    onBackground = Neutral900,
    surface = Neutral0,
    onSurface = Neutral900,
    onSurfaceVariant = Neutral600,
    outline = Neutral200,
    error = Coral400,
    secondaryContainer = Teal50,
    onSecondaryContainer = Teal900,
    surfaceContainer = Neutral0,
    surfaceContainerHigh = Neutral0,
    secondary = Teal600,
    onSecondary = Neutral0,
    tertiary = Blue400,
    onTertiary = Neutral0,
    tertiaryContainer = Blue50,
    onTertiaryContainer = Blue900,
    surfaceVariant = Gray50,
    surfaceContainerLowest = Neutral0,
    surfaceContainerLow = Neutral0,
    surfaceContainerHighest = Gray50,
    outlineVariant = Neutral200,
    errorContainer = Coral50,
    onErrorContainer = Coral900,
    onError = Neutral0,
    inverseSurface = Neutral900,
    inverseOnSurface = NeutralBg,
    scrim = Neutral900,
)

private val DarkColors = darkColorScheme(
    primary = Teal100,
    onPrimary = Teal900,
    primaryContainer = Teal900,
    onPrimaryContainer = Teal100,
    background = NeutralBgDark,
    onBackground = Neutral0,
    surface = NeutralSurfaceDark,
    onSurface = Neutral0,
    onSurfaceVariant = Neutral400,
    outline = Neutral600,
    error = Coral400,
    secondaryContainer = Teal900,
    onSecondaryContainer = Teal100,
    surfaceContainer = NeutralSurfaceDark,
    surfaceContainerHigh = NeutralSurfaceDark,
    secondary = Teal100,
    onSecondary = Teal900,
    tertiary = Blue400,
    onTertiary = Neutral0,
    tertiaryContainer = Blue900,
    onTertiaryContainer = Blue50,
    surfaceVariant = NeutralSurfaceDark,
    surfaceContainerLowest = NeutralBgDark,
    surfaceContainerLow = NeutralSurfaceDark,
    surfaceContainerHighest = Neutral600,
    outlineVariant = Neutral600,
    errorContainer = Coral900,
    onErrorContainer = Coral50,
    onError = Neutral0,
    inverseSurface = Neutral0,
    inverseOnSurface = Neutral900,
    scrim = Neutral900,

)

@Composable
fun JogaAiTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
){
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        content = content,
        typography = JogaAiTypography
    )
}