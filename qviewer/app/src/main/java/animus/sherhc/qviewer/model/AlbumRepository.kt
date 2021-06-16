package animus.sherhc.qviewer.model

import androidx.paging.Pager
import androidx.paging.PagingConfig
import animus.sherhc.qviewer.data.AlbumRemoteMediator
import animus.sherhc.qviewer.data.AppDatabase
import javax.inject.Inject

class AlbumRepository @Inject constructor(
	private val appDatabase: AppDatabase
) {
	companion object {
		private const val NETWORK_PAGE_SIZE = 20
	}

	fun getAlbum(id: Int) = Pager(
		config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
		remoteMediator = AlbumRemoteMediator(appDatabase, id),
	) { appDatabase.albumDao().getAlbumsById(id) }.flow
}