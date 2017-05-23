package com.biryanistudio.theemptywidget

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.widget.CheckBox
import org.jetbrains.anko.checkBox
import org.jetbrains.anko.frameLayout
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.sdk25.coroutines.onCheckedChange
import org.jetbrains.anko.wrapContent

class EnableColorsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var enableCheckBox: CheckBox? = null
        frameLayout {
            enableCheckBox = checkBox {
                text = "Color all the empty widgets"
                textSize = 22f
                gravity = Gravity.CENTER
                onCheckedChange { _, isChecked ->
                    val intent = intentFor<EmptyWidgetProvider>(EmptyWidgetProvider.ENABLE_COLORS to isChecked)
                    intent.action = "android.appwidget.action.APPWIDGET_UPDATE"
                    sendBroadcast(intent)
                }
            }.lparams(width = wrapContent, height = wrapContent, gravity = Gravity.CENTER)
        }
        enableCheckBox?.isChecked =
                getSharedPreferences("settings", Context.MODE_PRIVATE)
                        .getBoolean(EmptyWidgetProvider.ENABLE_COLORS, false)
    }
}
