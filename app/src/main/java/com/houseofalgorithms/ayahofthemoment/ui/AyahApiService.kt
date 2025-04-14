package com.houseofalgorithms.ayahofthemoment.ui

import com.houseofalgorithms.ayahofthemoment.data.AyahRepository
import com.houseofalgorithms.ayahofthemoment.data.model.AyahData

class AyahApiService {
    private val repository = AyahRepository()
    
    /**
     * Data class to hold the simplified ayah information needed for the widget
     */
    data class RandomAyah(
        val text: String,
        val surahName: String,
        val ayahNumber: Int
    )
    
    /**
     * Fetches a random ayah from the Quran API and converts it to a simplified format
     */
    suspend fun getRandomAyah(): RandomAyah {
        val response = repository.getRandomAyah()
        return RandomAyah(
            text = response.data.text,
            surahName = response.data.surah.englishName,
            ayahNumber = response.data.numberInSurah
        )
    }
}