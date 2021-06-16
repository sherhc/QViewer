package animus.sherhc.qviewer.data

import androidx.paging.PagingSource
import androidx.room.*
import animus.sherhc.qviewer.model.Album
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged

@Dao
interface AlbumDao {
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertAll(rules: List<Album>)

	@Update
	suspend fun updateAlbums(vararg album: Album)

	@Query("SELECT * FROM album WHERE idCode = :idCode")
	suspend fun albumsIdCode(idCode: String): Album?

	@Query("SELECT * FROM album")
	fun getAllAlbums(): Flow<Album>

	@Query("SELECT * FROM album WHERE albumId= :albumId")
	fun getAlbumsById(albumId: Int): PagingSource<Int, Album>

	@Query("DELETE FROM album")
	suspend fun clearAlbums()

	suspend fun getAlbumsDistinctUntilChanged() =
		getAllAlbums().distinctUntilChanged()
}