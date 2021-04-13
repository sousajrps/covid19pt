package com.sousajrps.covid19pt

import android.content.Context
import android.content.res.Configuration
import com.sousajrps.covid19pt.sharedPreferences.AppSharedPreferences
import com.sousajrps.covid19pt.sharedPreferences.AppSharedPreferencesModule
import java.util.*

object RuntimeLocaleChanger {

    fun wrapContext(context: Context): Context {

        val savedLocale = AppSharedPreferencesModule.getAppSharedPreferences().locale // load the user picked language from persistence (e.g SharedPreferences)
            ?: return context // else return the original untouched context

        // as part of creating a new context that contains the new locale we also need to override the default locale.
        Locale.setDefault(savedLocale)

        // create new configuration with the saved locale
        val newConfig = Configuration()
        newConfig.setLocale(savedLocale)

        return context.createConfigurationContext(newConfig)
    }
}
