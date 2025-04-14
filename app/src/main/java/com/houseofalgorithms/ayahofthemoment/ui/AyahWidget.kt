package com.houseofalgorithms.ayahofthemoment.ui

import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.padding
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import org.koin.core.context.GlobalContext
import org.koin.core.parameter.parametersOf

private const val TAG = "AyahWidget"

class AyahWidget : GlanceAppWidget() {
    private lateinit var viewModel: AyahViewModel

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        viewModel = GlobalContext.get().get<AyahViewModel>(parameters = { parametersOf(context) })
        provideContent {
            Column(
                modifier = GlanceModifier
                    .background(ColorProvider(Color.White))
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "آية اليوم",
                    style = TextStyle(
                        color = ColorProvider(Color.Black),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                )
                Text(
                    text = "Al-Baqarah: 255 (Ayatul Kursi)",
                    style = TextStyle(
                        color = ColorProvider(Color.Gray),
                        fontSize = 14.sp
                    )
                )
                Text(
                    text = "اللَّهُ لَا إِلَٰهَ إِلَّا هُوَ الْحَيُّ الْقَيُّومُ",
                    style = TextStyle(
                        color = ColorProvider(Color.Black),
                        fontSize = 16.sp
                    )
                )
            }
        }
    }
    // Data class to hold ayah information
    data class AyahInfo(
        val text: String,
        val surah: String
    )
}