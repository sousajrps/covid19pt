package com.sousajrps.covid19pt.sharedPreferences

object AppSharedPreferencesModule {
  private val sharedPreferences: AppSharedPreferences by lazy { AppSharedPreferencesUtils() }

  fun getAppSharedPreferences(): AppSharedPreferences = sharedPreferences
}
