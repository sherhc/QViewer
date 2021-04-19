package animus.sherhc.qviewer.data

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

    @Query("SELECT * FROM album")
    fun getAllAlbums(): Flow<Album>

    suspend fun getAlbumsDistinctUntilChanged() =
        getAllAlbums().distinctUntilChanged()
}