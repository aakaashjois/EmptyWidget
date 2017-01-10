package com.biryanistudio.emptywidget;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.CompoundButton;

public class EnableColorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enable_colors);
        CheckBox enableColors = (CheckBox) findViewById(R.id.enable_colors);
        SharedPreferences settings = getSharedPreferences("settings", MODE_PRIVATE);
        enableColors.setChecked(settings.getBoolean(EmptyWidgetProvider.ENABLE_COLORS, false));
        enableColors.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Intent intent = new Intent(getApplicationContext(), EmptyWidgetProvider.class);
                intent.setAction("android.appwidget.action.APPWIDGET_UPDATE");
                    intent.putExtra(EmptyWidgetProvider.ENABLE_COLORS, isChecked);
                    sendBroadcast(intent);
            }
        });
    }
}