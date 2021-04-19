package animus.sherhc.qviewer.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import animus.sherhc.qviewer.model.Album
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import java.io.IOException

@Suppress("BlockingMethodInNonBlockingContext")
class AlbumPaging(private val siteDao: SiteDao, private val id: Int) : PagingSource<Int, Album>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Album> =
        withContext(Dispatchers.IO) {
            try {
                var firstPage = 1
                val pageNumber = params.key ?: firstPage
                val url = siteDao.getSiteById(id).url
                val regexUrl = url.replace(Regex("\\{page:\\d+")) {
                    firstPage = it.value.drop(6).toInt()
                    "$pageNumber"
                }
                val document = Jsoup.connect(regexUrl).get()
                val elements = document.select(".li a img")
                val data =
                    elements
                        .map { element ->
                            Album(
                                idCode = element.parent().attr("href"),
                                title = element.attr("alt"),
                                cover = element.attr("src")
                            )
                        }
                val prevKey = if (pageNumber > firstPage) pageNumber - 1 else null
                val nextKey = pageNumber + 1
                return@withContext LoadResult.Page(data, prevKey, nextKey)
            } catch (e: IOException) {
                LoadResult.Error(e)
            }
        }

    override fun getRefreshKey(state: PagingState<Int, Album>) =
        state.anchorPosition?.let { pos ->
            val page = state.closestPageToPosition(pos)
            page?.prevKey?.plus(1) ?: page?.nextKey?.minus(1)
        }
}