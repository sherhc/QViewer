package animus.sherhc.qviewer.data

import androidx.room.Database
import androidx.room.RoomDatabase
import animus.sherhc.qviewer.model.Album
import animus.sherhc.qviewer.model.Site

@Database(entities = [Site::class, Album::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun siteDao(): SiteDao
    abstract fun albumDao(): AlbumDao
}