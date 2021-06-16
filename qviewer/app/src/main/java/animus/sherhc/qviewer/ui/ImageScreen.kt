package animus.sherhc.qviewer.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.paging.compose.collectAsLazyPagingItems
import animus.sherhc.qviewer.ImageViewModel
import com.google.accompanist.coil.rememberCoilPainter

@Composable
fun ImageScreen(viewModel: ImageViewModel, imageId: String?) {
	Scaffold(drawerContent = { Text("123") })
	{
		if (imageId != null) {
			val lazy = viewModel.getImage(imageId).collectAsLazyPagingItems()
			LazyVerticalGrid(cells = GridCells.Fixed(2)) {
				items(lazy.itemCount) {
					val value = lazy.getAsState(it).value
					Image(
						contentScale = ContentScale.FillWidth,
						painter = rememberCoilPainter(value?.thumbUrl),
						contentDescription = "",
						modifier = Modifier.fillMaxSize()
					)
				}
			}

		} else {
			Text("null")
		}
	}
}