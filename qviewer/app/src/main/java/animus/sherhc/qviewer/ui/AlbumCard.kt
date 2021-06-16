package animus.sherhc.qviewer.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import animus.sherhc.qviewer.model.Album
import com.google.accompanist.coil.rememberCoilPainter

@Composable
fun AlbumCard(modifier: Modifier = Modifier, album: Album?) {
	Box(Modifier.padding(4.dp)) {
		Card {
			Column(modifier) {
				Image(
					modifier = Modifier.fillMaxWidth(),
					contentScale = ContentScale.FillWidth,
					painter = rememberCoilPainter("${album?.cover}"),
					contentDescription = "图片",
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
}