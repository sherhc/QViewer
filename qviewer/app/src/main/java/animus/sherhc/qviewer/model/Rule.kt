package animus.sherhc.qviewer.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Site(
	@PrimaryKey @ColumnInfo(name = "rowid") val siteId: Int,
	val url: String,
	val title: String = "",
	val queryUrl: String? = null,
)

@Entity
data class Album(
	@PrimaryKey
	val idCode: String,
	val albumId: Int,
	val title: String? = null,
	val cover: String? = null,
	val createdTime: String? = null,
)

@Entity
data class Image(
	@PrimaryKey
	val thumbUrl: String,
	val href: String,
	//val url: String,
)