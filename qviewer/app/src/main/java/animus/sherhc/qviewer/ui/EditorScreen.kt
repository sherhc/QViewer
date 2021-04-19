package animus.sherhc.qviewer.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import animus.sherhc.qviewer.AlbumViewModel
import animus.sherhc.qviewer.R


@Preview
@Composable
fun EditorScreen() {
    QViewerTheme {
        Column {
            val viewModel: AlbumViewModel = viewModel()
            val url by viewModel.urlFlow.collectAsState(0)
            OutlinedTextField(
                value = "$url",
                onValueChange = { },
                label = { Text(stringResource(R.string.url)) },
                singleLine = true,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                trailingIcon = {
                    IconButton(
                        onClick = { viewModel.setUrl(0) },
                        content = { Icon(Icons.Default.Send, null) }
                    )
                }
            )
            OutlinedTextField(
                value = "$url",
                onValueChange = { },
                label = { Text(stringResource(R.string.url)) },
                singleLine = true,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                trailingIcon = {
                    IconButton(
                        onClick = { viewModel.setUrl(0) },
                        content = { Icon(Icons.Default.Send, null) }
                    )
                }
            )

        }
    }
}
