package animus.sherhc.qviewer.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.collectAsLazyPagingItems
import animus.sherhc.qviewer.AlbumViewModel

@Composable
fun AlbumScreen(modifier: Modifier = Modifier, viewModel: AlbumViewModel, rowId: Int?) {
    AlbumCardList(modifier, viewModel.getAlbum(0).collectAsLazyPagingItems(), 2)
}

@Composable
private fun HomePageTabs(
    modifier: Modifier = Modifier,
    pages: Array<Unit> = arrayOf(FavoritesScreen(), FavoritesScreen()),
    titles: Array<String> = arrayOf("1", "2"),
) {
    Column {
        var selectedIndex by remember { mutableStateOf(0) }
        ScrollableTabRow(
            selectedTabIndex = selectedIndex,
            tabs = {
                pages.indices.forEach { index ->
                    Tab(
                        selected = selectedIndex == index,
                        onClick = { selectedIndex = index },
                        text = {
                            Text(
                                text = titles[index],
                                style = MaterialTheme.typography.body2
                            )
                        }
                    )
                }
            }
        )
        //pages[selectedIndex]
    }

}
