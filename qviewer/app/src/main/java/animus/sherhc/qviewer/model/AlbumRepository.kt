package animus.sherhc.qviewer.model

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import animus.sherhc.qviewer.data.AlbumPaging
import animus.sherhc.qviewer.data.SiteDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AlbumRepository @Inject constructor(
    private val siteDao: SiteDao
) {
    fun getAlbum(id: Int): Flow<PagingData<Album>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
        ) { AlbumPaging(siteDao, id) }.flow
    }

    suspend fun getTitle(id: Int): String {
        return siteDao.getTitleByTitle(id)
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 20
    }
}