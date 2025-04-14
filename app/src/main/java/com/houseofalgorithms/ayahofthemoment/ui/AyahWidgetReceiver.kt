package com.houseofalgorithms.ayahofthemoment.ui

import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver

/**
 * Implementation of App Widget functionality.
 * This class is responsible for creating the widget and handling widget updates.
 */
class AyahWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = AyahWidget()
}