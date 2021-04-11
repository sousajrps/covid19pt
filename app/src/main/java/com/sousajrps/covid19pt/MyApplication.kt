package com.sousajrps.covid19pt

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.sousajrps.covid19pt.sharedPreferences.AppSharedPreferences
import com.sousajrps.covid19pt.sharedPreferences.AppSharedPreferencesModule

class MyApplication : Application() {
    private val sharedPreferences = AppSharedPreferencesModule.getAppSharedPreferences()
    override fun onCreate() {
        super.onCreate()
        sharedPreferences.initialize(this)
    }
}
