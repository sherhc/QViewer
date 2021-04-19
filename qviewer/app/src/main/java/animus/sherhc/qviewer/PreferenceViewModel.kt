package animus.sherhc.qviewer

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class PreferenceViewModel(private val app: Application) : AndroidViewModel(app) {
    //val urlFlow = MutableStateFlow("")

    init {
        viewModelScope.launch {
           /* app.dataStore.data
                .catch { exception ->
                    if (exception is IOException) {
                        emit(emptyPreferences())
                    } else {
                        throw exception
                    }
                }.collect { preferences ->
                    urlFlow.value =
                        preferences[stringPreferencesKey("url")] ?: "https://wnacg.org/"
                }*/

        }
    }

    fun setUrl(baseUrl: String) {
        viewModelScope.launch {
          /*  app.dataStore.edit { pref ->
                pref[stringPreferencesKey("url")] = baseUrl
            }*/
        }
    }
}