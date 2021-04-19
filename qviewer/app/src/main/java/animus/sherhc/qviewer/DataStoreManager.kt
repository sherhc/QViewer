package animus.sherhc.qviewer

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

private val Context.dataStore by preferencesDataStore("settings")

class DataStoreManager(appContext: Context) {
    private val dataStore = appContext.dataStore

    suspend fun setLastSite(index: Int) {
        dataStore.edit { pref ->
            pref[intPreferencesKey("lastSite")] = index
        }
    }

    val lastSite: Flow<Int?> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            preferences[intPreferencesKey("lastSite")]
        }
}