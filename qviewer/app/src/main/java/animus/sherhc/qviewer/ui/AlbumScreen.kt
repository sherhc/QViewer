package animus.sherhc.qviewer.ui

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
import androidx.compose.ui.input.pointer.pointerInput
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import animus.sherhc.qviewer.AlbumViewModel
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@Composable
fun AlbumScreen(navController: NavController, viewModel: AlbumViewModel) {
	val title by remember { mutableStateOf("绅士漫画") }
	val state = rememberScaffoldState()
	val coroutine = rememberCoroutineScope()
	val pages = listOf("同人志", "单行本", "杂志&短篇", "韩漫")
	val pagerState = rememberPagerState(pageCount = pages.size)
	Scaffold(
		topBar = {
			PreTopBar(
				title = title,
				onNavigationIconClick = { coroutine.launch { state.drawerState.open() } },
				onNavigate = {
					when (it) {
						"favorites" -> {
							navController.navigate("favorites")
						}
					}
				}) {
				ScrollableTabRow(
					selectedTabIndex = pagerState.currentPage,
					indicator = { tabPositions ->
						TabRowDefaults.Indicator(
							Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
						)
					}
				) {
					pages.forEachIndexed { index, title ->
						Tab(
							selected = pagerState.currentPage == index,
							onClick = {
								coroutine.launch {
									pagerState.animateScrollToPage(index)
								}
							},
							text = {
								Text(
									text = title,
									style = MaterialTheme.typography.body2
								)
							}
						)
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
		HorizontalPager(state = pagerState) { page ->
			AlbumCardList(
				navController,
				viewModel.getAlbum(page).collectAsLazyPagingItems(),
				2
			)
		}
	}
}
