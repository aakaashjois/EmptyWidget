package com.biryanistudio.theemptywidget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.RemoteViews
import java.util.*

/**
 * Created by Aakaash on 09/01/17 at 4:33 PM
 */

class EmptyWidgetProvider : AppWidgetProvider() {

    override fun onReceive(context: Context, intent: Intent) {
        val enableColors = intent.getBooleanExtra(ENABLE_COLORS, false)
        val settings = context.getSharedPreferences("settings", Context.MODE_PRIVATE)
        val editor = settings.edit()
        editor.putBoolean(ENABLE_COLORS, enableColors)
        editor.apply()
        addColors(context, enableColors)
    }

    override fun onEnabled(context: Context) {
        shouldAddColors(context)
    }

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        shouldAddColors(context)
    }

    override fun onAppWidgetOptionsChanged(context: Context, appWidgetManager: AppWidgetManager,
                                           appWidgetId: Int, newOptions: Bundle) {
        shouldAddColors(context)
    }

    override fun onDeleted(context: Context, appWidgetIds: IntArray) {
        shouldAddColors(context)
    }

    override fun onDisabled(context: Context) {
        shouldAddColors(context)
    }

    override fun onRestored(context: Context, oldWidgetIds: IntArray, newWidgetIds: IntArray) {
        shouldAddColors(context)
    }

    private fun addColors(context: Context, enableColors: Boolean = true) {
        val manager = AppWidgetManager.getInstance(context)
        manager.getAppWidgetIds(ComponentName(context, EmptyWidgetProvider::class.java)).forEach {
            val remoteViews = RemoteViews(context.packageName, R.layout.empty_widget_layout)
            remoteViews.setInt(R.id.empty_widget, "setBackgroundColor",
                    if (enableColors) colors[Random().nextInt(colors.size)] else Color.TRANSPARENT)
            manager.updateAppWidget(it, remoteViews)
        }

    }

    private fun shouldAddColors(context: Context) {
        if (context.getSharedPreferences("settings", Context.MODE_PRIVATE)
                .getBoolean(ENABLE_COLORS, false)) addColors(context)
    }

    companion object {
        val ENABLE_COLORS = "enable_colors"
        internal val colors = intArrayOf(
                Color.BLACK,
                Color.BLUE,
                Color.CYAN,
                Color.DKGRAY,
                Color.GRAY,
                Color.GREEN,
                Color.LTGRAY,
                Color.MAGENTA,
                Color.RED,
                Color.WHITE,
                Color.YELLOW)
    }
}
