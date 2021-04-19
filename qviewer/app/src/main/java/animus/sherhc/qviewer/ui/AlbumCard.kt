package animus.sherhc.qviewer.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import animus.sherhc.qviewer.model.Album
import dev.chrisbanes.accompanist.glide.GlideImage

@Composable
fun AlbumCard(modifier: Modifier = Modifier, album: Album?) {
    Card(modifier.padding(horizontal = 4.dp)) {
        Column {
            GlideImage(
                data = "https:${album?.cover}",
                contentDescription = "图片",
                modifier = Modifier.fillMaxSize(),
                loading = {
                    Box(Modifier.aspectRatio(0.75f)) {
                        CircularProgressIndicator(Modifier.align(Alignment.Center))
                    }
                },
            )
            album?.title?.let {
                Text(
                    text = it.replace(Regex("\\[(.*?)]"), ""),
                    modifier = Modifier
                        .padding(8.dp)
                        .height(40.dp),
                )
            }
        }
    }
}