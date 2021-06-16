package animus.sherhc.qviewer.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import animus.sherhc.qviewer.R

@Composable
fun PreTopBar(
	modifier: Modifier = Modifier,
	title: String = stringResource(R.string.app_name),
	onNavigationIconClick: () -> Unit = {},
	backgroundColor: Color = MaterialTheme.colors.primarySurface,
	contentColor: Color = contentColorFor(backgroundColor),
	elevation: Dp = AppBarDefaults.TopAppBarElevation,
	contentPadding: PaddingValues = AppBarDefaults.ContentPadding,
	onNavigate: (route: String) -> Unit = {},
	content: @Composable () -> Unit
) {
	Surface(
		color = backgroundColor,
		contentColor = contentColor,
		elevation = elevation,
		shape = RectangleShape,
		modifier = modifier,
	) {
		Column {
			Row(
				Modifier.fillMaxWidth()
					.padding(contentPadding)
					.height(AppBarHeight),
				horizontalArrangement = Arrangement.Start,
				verticalAlignment = Alignment.CenterVertically,
			) {
				Row(TitleIconModifier, verticalAlignment = Alignment.CenterVertically) {
					CompositionLocalProvider(
						LocalContentAlpha provides ContentAlpha.high,
					) {
						IconButton(
							onClick = onNavigationIconClick,
							content = {
								Icon(Icons.Default.Menu, stringResource(R.string.navigationIcon))
							}
						)
					}
				}
				Row(
					Modifier.fillMaxHeight().weight(1f),
					verticalAlignment = Alignment.CenterVertically
				) {
					ProvideTextStyle(value = MaterialTheme.typography.h6) {
						CompositionLocalProvider(
							LocalContentAlpha provides ContentAlpha.high,
						) { Text(title) }
					}
				}
				CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
					Row(
						Modifier.fillMaxHeight(),
						horizontalArrangement = Arrangement.End,
						verticalAlignment = Alignment.CenterVertically,
					) {
						FavoritesIcon { onNavigate("favorites") }
						IconButton(onClick = {}) {
							Icon(Icons.Default.Search, stringResource(R.string.search))
						}
					}
				}
			}
			content()
		}
	}

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

private val AppBarHeight = 56.dp
private val AppBarHorizontalPadding = 4.dp
private val TitleIconModifier = Modifier.fillMaxHeight()
	.width(72.dp - AppBarHorizontalPadding)