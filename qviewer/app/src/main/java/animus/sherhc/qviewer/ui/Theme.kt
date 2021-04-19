package animus.sherhc.qviewer.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkPalette = darkColors(
    primary = Color(0xFF1EB980),
    primaryVariant = Color(0xFF1EB980),
    onPrimary = Color.Black,
    secondary = Color(0xFFb91e57),
    onSecondary = Color.White,
)

private val LightPalette = lightColors(
    primary = Color(0xFF3377FF),
    primaryVariant = Color(0xFF3377FF),
    onPrimary = Color.White,
    secondary = Color(0xFFffbb33),
    onSecondary = Color.Black,
)

@Composable
fun QViewerTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkPalette
    } else {
        LightPalette
    }
    MaterialTheme(
        colors = colors,
        content = content
    )
}