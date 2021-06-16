package animus.sherhc.qviewer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import animus.sherhc.qviewer.ui.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			QViewerTheme {
				window.statusBarColor = MaterialTheme.colors.primarySurface.toArgb()
				MainContent()
			}
		}
	}

	@Composable
	fun MainContent() {
		val navController = rememberNavController()
		val albumViewModel: AlbumViewModel = viewModel()
		val imageViewModel: ImageViewModel = viewModel()
		NavHost(navController, startDestination = "album?rowid={rowid}") {
			composable(
				route = "album?rowid={rowid}",
				arguments = listOf(navArgument("rowid") { nullable = true })
			) {
				AlbumScreen(navController, albumViewModel)
			}
			composable(
				route = "images?imageId={imageId}",
				arguments = listOf(navArgument("imageId") { type = NavType.StringType })
			) { entry ->
				ImageScreen(imageViewModel, entry.arguments?.getString("imageId"))
			}
			composable("favorites") { FavoritesScreen() }
			composable("RuleEditor") { EditorScreen() }
		}
	}
}

