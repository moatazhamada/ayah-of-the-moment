package com.houseofalgorithms.ayahofthemoment.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.houseofalgorithms.ayahofthemoment.data.AyahRepository
import com.houseofalgorithms.ayahofthemoment.data.model.AyahResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import java.io.File

class AyahViewModel(private val repository: AyahRepository, private val context: Context) : ViewModel() {
    private val _ayahState = MutableStateFlow<AyahResponse?>(null)
    val ayahState: StateFlow<AyahResponse?> = _ayahState

    private val dataStore: DataStore<Preferences> = PreferenceDataStoreFactory.create(
        produceFile = { File(context.filesDir, "datastore/ayah_preferences.preferences_pb") }
    )

    fun fetchRandomAyah() {
        viewModelScope.launch {
            try {
                val response = repository.getRandomAyah()
                _ayahState.value = response
                // Update preferences when new ayah is fetched
                dataStore.edit { preferences ->
                    preferences[stringPreferencesKey(ayahTextKey.name)] = response.data.text
                    preferences[stringPreferencesKey(ayahSurahKey.name)] = "${response.data.surah.englishName}: ${response.data.numberInSurah}"
                }
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    fun getPreferences(): Flow<Preferences> {
        return dataStore.data
    }
}