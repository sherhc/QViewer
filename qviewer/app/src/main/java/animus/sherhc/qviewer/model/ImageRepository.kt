package animus.sherhc.qviewer.model

import androidx.paging.Pager
import androidx.paging.PagingConfig
import animus.sherhc.qviewer.data.ImagePaging
import javax.inject.Inject

class ImageRepository @Inject constructor() {
	companion object {
		private const val NETWORK_PAGE_SIZE = 1
	}

	fun getImage(code: String) = Pager(
		PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
	) {
		ImagePaging(code)
	}.flow

}