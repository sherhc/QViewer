package animus.sherhc.qviewer.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import animus.sherhc.qviewer.model.Image
import kotlinx.coroutines.*
import org.jsoup.Jsoup
import java.io.IOException

@Suppress("BlockingMethodInNonBlockingContext")
class ImagePaging(private val code: String) :
	PagingSource<Int, Image>() {
	override fun getRefreshKey(state: PagingState<Int, Image>): Int? {
		TODO("Not yet implemented")
	}

	override suspend fun load(params: LoadParams<Int>) =
		withContext(Dispatchers.IO) {
			try {
				var firstPage = 1
				val pageNumber = params.key ?: firstPage
				val url = "http://wnacg.org/photos-index-page-{page:1}-$code.html"
				val regexUrl = url.replace(Regex("\\{page:[\\d+]\\}")) {
					firstPage = it.value.drop(6).dropLast(1).toInt()
					"$pageNumber"
				}
				val document = Jsoup.connect(regexUrl).get()
				val elements = document.select("div.pic_box.tb > a > img")
				val data = elements.map {
					val href = it.parent().attr("abs:href")
					/*val imageUrl = Jsoup.connect(href).get().select("#picarea").first()
						.attr("abs:src")*/
					Image(
						thumbUrl = it.attr("abs:src"),
						href = href,
						//url = imageUrl,
					)
				}
				return@withContext LoadResult.Page(
					data = data,
					prevKey = if (pageNumber > 1) pageNumber - 1 else null,
					nextKey = pageNumber + 1
				)
			} catch (e: IOException) {
				return@withContext LoadResult.Error(e)
			}
		}

}