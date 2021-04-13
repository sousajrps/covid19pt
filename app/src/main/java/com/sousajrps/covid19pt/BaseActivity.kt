package com.sousajrps.covid19pt

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.util.*

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N_MR1) {
            changeLanguage(baseContext)
        }
        changeLanguage(this)
    }

    override fun attachBaseContext(context: Context) {
        val newContext = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            changeLanguage(context)
        } else {
            context
        }
        super.attachBaseContext(newContext)
    }

    private fun changeLanguage(context: Context): Context {
        val locale: Locale = AppModule.getAppSharedPreferences().locale ?: Locale.getDefault()
        return if (context.resources.configuration.locale != locale) {
            ContextUtils().updateLocale(context, locale)
        } else {
            context
        }
    }
}
