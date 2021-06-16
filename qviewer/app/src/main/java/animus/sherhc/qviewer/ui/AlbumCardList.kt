package animus.sherhc.qviewer.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState.Error
import androidx.paging.compose.LazyPagingItems
import animus.sherhc.qviewer.model.Album

@Composable
fun AlbumCardList(
	navController: NavController,
	lazyItems: LazyPagingItems<Album>,
	rows: Int = 2
) {
	val state = rememberLazyListState()
	val showDialog = remember { mutableStateOf(true) }
	LazyVerticalGrid(
		cells = GridCells.Fixed(rows),
		state = state,
		contentPadding = PaddingValues(4.dp),
	) {
		when (val refreshState = lazyItems.loadState.refresh) {
			is Error -> {
				if (showDialog.value) {
					item {
						ErrorAlert(refreshState.error, showDialog.value) {
							showDialog.value = false
							lazyItems.retry()
						}
					}
				}
			}
		}
		when (val appendState = lazyItems.loadState.append) {
			is Error -> {
				if (showDialog.value) {
					item {
						ErrorAlert(appendState.error, showDialog.value) {
							showDialog.value = false
							lazyItems.retry()
						}
					}
				}
			}

		}
		items(lazyItems.itemCount) {
			val value = lazyItems.getAsState(it).value
			AlbumCard(
				modifier = Modifier.clickable {
					val code = Regex("aid-(\\d+)").find(
						value?.idCode!!
					)?.value
					navController.navigate(
						"images?imageId=$code"
					) {
						launchSingleTop = true
					}
				},
				album = value
			)
		}
	}
}

