package animus.sherhc.qviewer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import animus.sherhc.qviewer.model.AlbumRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumViewModel @Inject constructor(
    private val repository: AlbumRepository,
    private val dataStore: DataStoreManager
) : ViewModel() {
    fun getAlbum(id: Int) = repository.getAlbum(id).cachedIn(viewModelScope)
    suspend fun getTitle(id: Int) = repository.getTitle(id)

    val urlFlow = dataStore.lastSite

    fun setUrl(index: Int) {
        viewModelScope.launch {
            dataStore.setLastSite(index)
        }
    }

}