package com.houseofalgorithms.ayahofthemoment.ui

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AyahRepository {
    /**
     * Data class to represent the API response structure
     */
    data class AyahResponse(
        val code: Int,
        val status: String,
        val data: AyahData
    )
    
    data class AyahData(
        val number: Int,
        val text: String,
        val surah: SurahInfo,
        val numberInSurah: Int
    )
    
    data class SurahInfo(
        val number: Int,
        val name: String,
        val englishName: String
    )
    
    /**
     * Fetches a random ayah from the API or returns a fallback if the request fails
     */
    suspend fun getRandomAyah(): AyahResponse {
        return try {
            // Simulate API call - in a real app, this would be a network request
            // to an actual Quran API endpoint
            withContext(Dispatchers.IO) {
                // Network request would go here
                throw Exception("Network request simulation")
            }
        } catch (e: Exception) {
            // Return fallback ayah when network request fails
            AyahResponse(
                code = 200,
                status = "OK",
                data = AyahData(
                    number = 262,
                    text = "اللَّهُ لَا إِلَٰهَ إِلَّا هُوَ الْحَيُّ الْقَيُّومُ",
                    surah = SurahInfo(
                        number = 2,
                        name = "البقرة",
                        englishName = "Al-Baqarah"
                    ),
                    numberInSurah = 255
                )
            )
        }
    }
}