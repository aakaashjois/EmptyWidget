package com.biryanistudio.emptywidget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.widget.RemoteViews;

import java.util.Random;

/**
 * Created by Aakaash on 09/01/17 at 4:33 PM.
 */

public class EmptyWidgetProvider extends AppWidgetProvider {
    public static final String ENABLE_COLORS = "enable_colors";
    static final int[] colors = {Color.BLACK, Color.BLUE, Color.CYAN, Color.DKGRAY, Color.GRAY,
            Color.GREEN, Color.LTGRAY, Color.MAGENTA, Color.RED, Color.WHITE, Color.YELLOW};

    @Override
    public void onReceive(Context context, Intent intent) {
        Boolean enableColors = intent.getBooleanExtra(ENABLE_COLORS, false);
        SharedPreferences settings = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(ENABLE_COLORS, enableColors);
        editor.apply();
        AppWidgetManager manager = AppWidgetManager.getInstance(context);
        int ids[] = manager.getAppWidgetIds(new ComponentName(context,
                EmptyWidgetProvider.class));
        for (int id : ids) {
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
                    R.layout.empty_widget_layout);
            remoteViews.setInt(R.id.empty_widget, "setBackgroundColor",
                    enableColors ? colors[new Random().nextInt(colors.length)] : Color.TRANSPARENT);
            manager.updateAppWidget(id, remoteViews);
        }
    }
}
