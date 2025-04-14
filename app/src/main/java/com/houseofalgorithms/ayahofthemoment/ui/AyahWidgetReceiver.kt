package com.houseofalgorithms.ayahofthemoment.ui

import android.content.Context
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.work.*
import java.util.concurrent.TimeUnit

class AyahWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = AyahWidget()

    override fun onEnabled(context: Context) {
        super.onEnabled(context)
        enqueueWidgetWorker(context)
    }

    private fun enqueueWidgetWorker(context: Context) {
        val request = PeriodicWorkRequestBuilder<AyahWorker>(12, TimeUnit.HOURS)
            .setConstraints(
                Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build()
            )
            .build()

        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            "AyahWorker",
            ExistingPeriodicWorkPolicy.KEEP,
            request
        )
    }
}