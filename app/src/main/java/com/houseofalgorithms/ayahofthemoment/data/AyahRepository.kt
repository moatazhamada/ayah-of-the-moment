package com.houseofalgorithms.ayahofthemoment.data

import com.houseofalgorithms.ayahofthemoment.data.model.AyahResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface AyahApiService {
    @GET("quran/{edition}/random")
    suspend fun getRandomAyah(@Path("edition") edition: String = "quran-simple"): AyahResponse
}

class AyahRepository {
    private val apiService: AyahApiService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.alquran.cloud/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiService = retrofit.create(AyahApiService::class.java)
    }

    suspend fun getRandomAyah(): AyahResponse {
        return apiService.getRandomAyah()
    }
}