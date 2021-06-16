package animus.sherhc.qviewer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import animus.sherhc.qviewer.model.ImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ImageViewModel @Inject constructor(
	private val image: ImageRepository,
) : ViewModel() {
	fun getImage(code: String) = image.getImage(code).cachedIn(viewModelScope)
}