package animus.sherhc.qviewer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.datastore.preferences.core.mutablePreferencesOf
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.*
import animus.sherhc.qviewer.ui.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

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
        val title by remember { mutableStateOf("绅士漫画") }

        val state = rememberScaffoldState()
        val navController = rememberNavController()
        val coroutine = rememberCoroutineScope()
        Scaffold(
            topBar = {
                PreTopBar(onNavigationIconClick = { coroutine.launch { state.drawerState.open() } }) {
                    when (it) {
                        "favorites" -> {
                            navController.navigate("favorites")
                        }
                    }
                }
            },
            drawerContent = {
                Box(
                    modifier = Modifier
                        .aspectRatio(1.778f),
                ) {
                    Column {
                        ListItem(
                            text = { Text(title, style = MaterialTheme.typography.h5) },
                            modifier = Modifier.pointerInput("a") {
                                detectTapGestures(
                                    onTap = {
                                        navController.navigate("album?rowid=0") {
                                            launchSingleTop = true
                                        }
                                    },
                                    onLongPress = {
                                        navController.navigate("RuleEditor") {
                                            launchSingleTop = true
                                        }
                                    }
                                )
                            }
                        )
                        Divider()
                        IconToggleButton(
                            checked = true,
                            onCheckedChange = { navController.navigate("RuleEditor") },
                            modifier = Modifier.fillMaxWidth(),
                        ) { Icon(Icons.Default.Add, "添加") }
                    }
                }
            },
            scaffoldState = state,
        ) {
            val viewModel: AlbumViewModel = viewModel()
            NavHost(navController, startDestination = "album?rowid={rowid}") {
                composable(
                    route = "album?rowid={rowid}",
                    arguments = listOf(navArgument("rowid") { nullable = true })
                ) {
                    val modifier = Modifier.clickable {
                        navController.navigate("images") {
                            launchSingleTop = true
                        }
                    }
                    AlbumScreen(modifier, viewModel, it.arguments?.getInt("rowid"))
                }
                composable("images") { ImageScreen() }
                composable("favorites") { FavoritesScreen() }
                composable("PageDetail") { PageScreen() }
                composable("RuleEditor") { EditorScreen() }
            }
        }
    }
}

