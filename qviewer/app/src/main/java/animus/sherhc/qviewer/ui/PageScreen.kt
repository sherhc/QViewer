package animus.sherhc.qviewer.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import animus.sherhc.qviewer.R

@Composable
fun PageScreen() {
    LazyRow {
        items(5) {
            Image(
                painter = painterResource(id = R.drawable.abc_vector_test),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth()
            )

        }
    }
}