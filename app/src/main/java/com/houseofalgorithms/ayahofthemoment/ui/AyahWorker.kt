package com.houseofalgorithms.ayahofthemoment.ui

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.glance.appwidget.state.updateAppWidgetState
import androidx.glance.state.PreferencesGlanceStateDefinition
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.flow.first
import org.koin.core.context.GlobalContext
import org.koin.core.parameter.parametersOf

private const val TAG = "AyahWorker"

val ayahTextKey = stringPreferencesKey("ayah_text")
val ayahSurahKey = stringPreferencesKey("ayah_surah")

class AyahWorker(
    private val context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {

    private val viewModel: AyahViewModel by lazy {
        GlobalContext.get().get<AyahViewModel>(parameters = { parametersOf(context) })
    }

    override suspend fun doWork(): Result {
        return try {
            Log.d(TAG, "Fetching Ayah from worker...")
            viewModel.fetchRandomAyah()
            val ayahState = viewModel.ayahState.first { it != null }

            val text = ayahState!!.data.text
            val surah = "${ayahState.data.surah.englishName}: ${ayahState.data.numberInSurah}"

            Log.d(TAG, "Fetching Ayah text = $text")
            Log.d(TAG, "Fetching Ayah surah = $surah")

            val glanceManager = GlanceAppWidgetManager(context)
            val widgetIds = glanceManager.getGlanceIds(AyahWidget::class.java)

            widgetIds.forEach { glanceId ->
                Log.d(TAG, "glanceId = $glanceId")

                updateAppWidgetState(
                    context = context,
                    definition = PreferencesGlanceStateDefinition,
                    glanceId = glanceId
                ) { prefs ->
                    prefs.toMutablePreferences().apply {
                        this[ayahTextKey] = text
                        this[ayahSurahKey] = surah
                    }
                }
                AyahWidget().update(context, glanceId)
            }

            Result.success()
        } catch (e: Exception) {
            Log.e(TAG, "Error fetching ayah: ${e.message}", e)
            Result.failure()
        }
    }
}