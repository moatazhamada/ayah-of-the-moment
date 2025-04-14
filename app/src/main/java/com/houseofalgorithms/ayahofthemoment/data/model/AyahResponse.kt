package com.houseofalgorithms.ayahofthemoment.data.model

data class AyahResponse(
    val code: Int,
    val status: String,
    val data: AyahData
)

data class AyahData(
    val number: Int,
    val text: String,
    val surah: Surah,
    val numberInSurah: Int
)

data class Surah(
    val number: Int,
    val name: String,
    val englishName: String,
    val englishNameTranslation: String
)