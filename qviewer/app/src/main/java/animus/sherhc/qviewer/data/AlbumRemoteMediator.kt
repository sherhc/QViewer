package animus.sherhc.qviewer.data

import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import animus.sherhc.qviewer.model.Album
import animus.sherhc.qviewer.model.RemoteKeys
import coil.network.HttpException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import java.io.IOException
import java.net.SocketTimeoutException


class AlbumRemoteMediator(private val db: AppDatabase, private val id: Int) :
	RemoteMediator<Int, Album>() {
	override suspend fun load(loadType: LoadType, state: PagingState<Int, Album>): MediatorResult {

		val url = when (id) {
			0 -> "http://wnacg.org/albums-index-page-{page:1}-cate-1.html"
			1 -> "http://wnacg.org/albums-index-page-{page:1}-cate-9.html"
			2 -> "http://wnacg.org/albums-index-page-{page:1}-cate-10.html"
			3 -> "http://wnacg.org/albums-index-page-{page:1}-cate-20.html"
			else -> "http://wnacg.org/albums-index-page-{page:1}.html"
		}
		var firstPage = 1
		val page = when (loadType) {
			LoadType.REFRESH -> {
				val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
				remoteKeys?.nextKey?.minus(1) ?: firstPage
			}
			LoadType.PREPEND -> {
				val remoteKeys = getRemoteKeyForFirstItem(state)
				val prevKey = remoteKeys?.prevKey
					?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
				prevKey
			}
			LoadType.APPEND -> {
				val remoteKeys = getRemoteKeyForLastItem(state)
				val nextKey = remoteKeys?.nextKey
					?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
				nextKey
			}
		}
		val regexUrl = url.replace(Regex("\\{page:\\d+\\}")) {
			firstPage = it.value.drop(6).dropLast(1).toInt()
			"$page"
		}
		return withContext(Dispatchers.IO) {
			try {
				/*val okhttp = OkHttpClient()
			val request = Request.Builder().url(regexUrl).get().build()*/

				//val document = Jsoup.parse(okhttp.newCall(request).execute().toString())
				val document = Jsoup.connect(regexUrl).timeout(3000).get()
				val elements = document.select("div.pic_box a img")
				val endOfPaginationReached = elements.isEmpty()
				db.withTransaction {
					if (loadType == LoadType.REFRESH) {
						db.remoteKeysDao().clearRemoteKeys()
						db.albumDao().clearAlbums()
					}
					val prevKey = if (page == 1) null else page - 1
					val nextKey = if (endOfPaginationReached) null else page + 1
					val keys = elements.map { element ->
						RemoteKeys(
							idCode = element.parent().attr("href"),
							prevKey = prevKey,
							nextKey = nextKey
						)
					}
					val data =
						elements
							.map { element ->
								Album(
									idCode = element.parent().attr("href"),
									albumId = id,
									title = element.attr("alt"),
									cover = element.attr("abs:src")
								)
							}
					db.remoteKeysDao().insertAll(keys)
					db.albumDao().insertAll(data)
				}
				MediatorResult.Success(endOfPaginationReached = false)
			} catch (exception: IOException) {
				MediatorResult.Error(exception)
			} catch (exception: HttpException) {
				MediatorResult.Error(exception)
			} catch (exception: SocketTimeoutException) {
				MediatorResult.Error(exception)
			}
		}
	}


	private suspend fun getRemoteKeyClosestToCurrentPosition(
		state: PagingState<Int, Album>
	): RemoteKeys? = state.anchorPosition?.let { position ->
		state.closestItemToPosition(position)?.idCode?.let { idCode ->
			db.remoteKeysDao().remoteKeysIdCode(idCode)
		}
	}


	private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, Album>): RemoteKeys? =
		state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
			?.let { album ->
				db.remoteKeysDao().remoteKeysIdCode(album.idCode)
			}


	private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, Album>): RemoteKeys? =
		state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
			?.let { album ->
				db.remoteKeysDao().remoteKeysIdCode(album.idCode)
			}
}