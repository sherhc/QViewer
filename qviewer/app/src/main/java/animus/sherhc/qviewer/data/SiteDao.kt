package animus.sherhc.qviewer.data

import androidx.room.*
import animus.sherhc.qviewer.model.Site
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged

@Dao
interface SiteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertSite(vararg site: Site)

    @Update
    suspend fun updateSites(vararg site: Site)

    @Query("SELECT * FROM site")
    fun getAllSites(): Flow<Site>

    @Query("SELECT * FROM site where rowid = :id")
    suspend fun getSiteById(id: Int): Site

    @Query("SELECT title FROM site where rowid = :id")
    suspend fun getTitleByTitle(id: Int): String
    suspend fun getSitesDistinctUntilChanged() =
        getAllSites().distinctUntilChanged()

}