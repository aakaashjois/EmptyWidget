package com.biryanistudio.emptywidget

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.widget.CheckBox
import org.jetbrains.anko.checkBox
import org.jetbrains.anko.frameLayout
import org.jetbrains.anko.sdk25.coroutines.onCheckedChange
import org.jetbrains.anko.wrapContent

class EnableColorsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var enableCheckBox: CheckBox? = null
        frameLayout {
            enableCheckBox = checkBox {
                text = "Enable Colors for all widgets"
                textSize = 22f
                gravity = Gravity.CENTER
                onCheckedChange { _, isChecked ->
                    val intent = Intent(applicationContext, EmptyWidgetProvider::class.java)
                    intent.action = "android.appwidget.action.APPWIDGET_UPDATE"
                    intent.putExtra(EmptyWidgetProvider.ENABLE_COLORS, isChecked)
                    sendBroadcast(intent)
                }
            }.lparams(width = wrapContent, height = wrapContent, gravity = Gravity.CENTER)
        }
        enableCheckBox?.isChecked =
                getSharedPreferences("settings", Context.MODE_PRIVATE)
                        .getBoolean(EmptyWidgetProvider.ENABLE_COLORS, false)
    }
}
