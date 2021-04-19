package animus.sherhc.qviewer.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState.Error
import androidx.paging.LoadState.Loading
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemsIndexed
import animus.sherhc.qviewer.model.Album

@Composable
fun AlbumCardList(modifier: Modifier = Modifier, lazyItems: LazyPagingItems<Album>, rows: Int = 2) {
    LazyColumn(modifier = Modifier.padding(horizontal = 8.dp)) {
        when (val refreshState = lazyItems.loadState.refresh) {
            is Loading -> {
                item {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.CenterHorizontally)
                    )
                }
            }
            is Error -> {
                item {
                    Text(refreshState.error.localizedMessage ?: "")
                }
            }
        }
        when (val appendState = lazyItems.loadState.append) {
            is Loading -> {
                item {
                    Text("正在加载...")
                }
            }
            is Error -> {
                item {
                    Text(appendState.error.localizedMessage ?: "")
                }
            }
        }
        itemsIndexed(lazyItems) { index, _ ->
            Row(modifier = Modifier.padding(top = 8.dp)) {
                (0 until rows).forEach {
                    Box(Modifier.weight(1f)) {
                        AlbumCard(
                            modifier = modifier,
                            album = lazyItems[rows * index + it]
                        )
                    }
                }
            }
        }

    }
}

