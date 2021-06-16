package animus.sherhc.qviewer.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun FavoritesScreen(items: Flow<PagingData<String>> = emptyFlow()) {
	val lazyPagingItems = items.collectAsLazyPagingItems()
	LazyColumn {
		items(lazyPagingItems) {
			Text(text = "123")
		}
	}
}