package animus.sherhc.qviewer.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class RemoteKeys(
	@PrimaryKey
	val idCode: String,
	val prevKey: Int?,
	val nextKey: Int?
)
