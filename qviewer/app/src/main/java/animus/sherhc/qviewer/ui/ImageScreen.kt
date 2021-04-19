package animus.sherhc.qviewer.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import animus.sherhc.qviewer.AlbumViewModel
import dev.chrisbanes.accompanist.glide.GlideImage

@Composable
fun ImageScreen() {
    val viewModel: AlbumViewModel = viewModel()
    val images = listOf<String>()
    LazyColumn {
        items(images) {
            GlideImage(data = it, modifier = Modifier.fillMaxWidth()) {

            }
        }
    }
}