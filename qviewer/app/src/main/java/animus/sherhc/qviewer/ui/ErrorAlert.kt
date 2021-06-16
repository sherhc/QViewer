package animus.sherhc.qviewer.ui

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable

@Composable
fun ErrorAlert(
	throwable: Throwable,
	showError: Boolean,
	onDismissRequest: () -> Unit
) {
	if (showError) {
		AlertDialog(
			text = { Text(throwable.localizedMessage ?: "null") },
			onDismissRequest = onDismissRequest,
			dismissButton = {
				TextButton(onClick = onDismissRequest) {
					Text("退出")
				}
			},
			confirmButton = {}
		)
	}
}
