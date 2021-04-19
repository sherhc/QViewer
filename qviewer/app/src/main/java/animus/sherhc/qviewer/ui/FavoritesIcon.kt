package animus.sherhc.qviewer.ui

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable

@Composable
fun FavoritesIcon(onNavigate: () -> Unit = {}) {
    IconButton(onClick = onNavigate) {
        Icon(Icons.Default.Star, null)
    }
}