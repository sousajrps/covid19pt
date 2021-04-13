package com.sousajrps.covid19pt

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import android.os.LocaleList
import java.util.*

class ContextUtils {
    fun updateLocale(c: Context, localeToSwitchTo: Locale): Context {
        var context = c
        val resources: Resources = context.resources
        val configuration: Configuration = resources.configuration
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val localeList = LocaleList(localeToSwitchTo)
            LocaleList.setDefault(localeList)
            configuration.setLocales(localeList)
        } else {
            configuration.locale = localeToSwitchTo
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            context = context.createConfigurationContext(configuration)
            resources.updateConfiguration(configuration, resources.displayMetrics)
        } else {
            resources.updateConfiguration(configuration, resources.displayMetrics)
        }
        return context
    }
}
