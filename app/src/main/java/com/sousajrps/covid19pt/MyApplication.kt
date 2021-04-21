package com.sousajrps.covid19pt

import android.app.Application
import com.sousajrps.covid19pt.local.LocalModule
import com.sousajrps.covid19pt.sharedPreferences.AppSharedPreferencesModule

class MyApplication : Application() {
    private val sharedPreferences = AppSharedPreferencesModule.getAppSharedPreferences()
    override fun onCreate() {
        super.onCreate()
        sharedPreferences.initialize(this)
        LocalModule.initializeDatabase(this)
    }
}
