package animus.sherhc.qviewer.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import animus.sherhc.qviewer.model.RemoteKeys

@Dao
interface RemoteKeyDao {
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertAll(remoteKey: List<RemoteKeys>)

	@Query("SELECT * FROM remote_keys WHERE idCode = :idCode")
	suspend fun remoteKeysIdCode(idCode: String): RemoteKeys?

	@Query("DELETE FROM remote_keys")
	suspend fun clearRemoteKeys()
}