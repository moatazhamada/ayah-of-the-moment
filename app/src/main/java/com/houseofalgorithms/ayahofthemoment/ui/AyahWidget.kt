package com.houseofalgorithms.ayahofthemoment.ui

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Column
import androidx.glance.layout.padding
import androidx.glance.layout.Alignment
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AyahWidget : GlanceAppWidget() {
    private val apiService = AyahApiService()

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        // Fetch data before providing content
        val ayahInfo = try {
            withContext(Dispatchers.IO) {
                val randomAyah = apiService.getRandomAyah()
                AyahInfo(
                    text = randomAyah.text,
                    surah = "${randomAyah.surahName}: ${randomAyah.ayahNumber}"
                )
            }
        } catch (e: Exception) {
            // Provide a meaningful fallback Ayah when API request fails
            AyahInfo(
                text = "اللَّهُ لَا إِلَٰهَ إِلَّا هُوَ الْحَيُّ الْقَيُّومُ",
                surah = "Al-Baqarah: 255 (Ayatul Kursi)"
            )
        }
        
        // Use the correct method signature for provideContent
        provideContent { WidgetContent(ayahInfo) }
    }
    
    // Data class to hold ayah information
    private data class AyahInfo(
        val text: String,
        val surah: String
    )

    @Composable
    private fun WidgetContent(ayahInfo: AyahInfo) {

        Column(
            modifier = GlanceModifier
                .background(GlanceTheme.colors.background)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "آية اليوم",
                style = TextStyle(
                    color = GlanceTheme.colors.onBackground,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            )
            Text(
                text = ayahInfo.surah,
                style = TextStyle(
                    color = GlanceTheme.colors.onSurfaceVariant,
                    fontSize = 14.sp
                )
            )
            Text(
                text = ayahInfo.text,
                style = TextStyle(
                    color = GlanceTheme.colors.onBackground,
                    fontSize = 16.sp
                )
            )
        }
    }
}