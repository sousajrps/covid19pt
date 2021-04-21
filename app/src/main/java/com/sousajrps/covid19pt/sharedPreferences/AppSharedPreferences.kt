package com.sousajrps.covid19pt.sharedPreferences

import android.content.Context
import java.util.*

interface AppSharedPreferences {
    var sharedPreferencesVersion: Int
    var dataTimeStamp: Long
    var vaccinationTimeStamp: Long
    var vaccinationWeeklyTimeStamp: Long
    var nightMode: String
    var locale: Locale?

    fun initialize(context: Context)

    fun clearAll()

    companion object {
        const val MODE_NIGHT_YES = "mode_night_yes"
        const val MODE_NIGHT_NO = "mode_night_no"
        const val LANGUAGE_EN = "en"
        const val LANGUAGE_PT = "pt"
        const val COUNTRY_US = "US"
        const val COUNTRY_PT = "PT"
    }
}
