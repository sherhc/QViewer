package animus.sherhc.qviewer.ui

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import animus.sherhc.qviewer.AlbumViewModel
import animus.sherhc.qviewer.R

@Preview
@Composable
fun PreTopBar(
    title: String = stringResource(R.string.app_name),
    onNavigationIconClick: () -> Unit = {},
    onNavigate: (route: String) -> Unit = {}
) {
    val viewModel: AlbumViewModel = viewModel()
    TopAppBar(
        navigationIcon = {
            IconButton(
                onClick = onNavigationIconClick,
                content = {
                    Icon(Icons.Default.Menu, stringResource(R.string.navigationIcon))
                }
            )
        },
        title = {
            //Text(viewModel.getTitle(0))
            Text("")
        },
        actions = {
            FavoritesIcon { onNavigate("favorites") }
            IconButton(onClick = {
            }) {
                Icon(Icons.Default.Search, stringResource(R.string.search))
            }
        })

    /* var text by remember { mutableStateOf("") }
     Surface(
         modifier = Modifier.fillMaxWidth(),
         color = MaterialTheme.colors.primarySurface,
         elevation = 8.dp
     ) {
         Row(
             modifier = Modifier
                 .fillMaxWidth()
                 .height(56.dp)
         ) {
             TextField(
                 value = text,
                 onValueChange = { search ->
                     text = search
                 },
                 trailingIcon = {
                     Icon(
                         Icons.Default.Close,
                         null,
                         modifier = Modifier.clickable {})
                 },
                 modifier = Modifier
                     .fillMaxWidth()
                     .padding(8.dp)
             )
         }
     }*/

}