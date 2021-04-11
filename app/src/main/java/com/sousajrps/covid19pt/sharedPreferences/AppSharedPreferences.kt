package com.sousajrps.covid19pt.sharedPreferences

import android.content.Context
import io.reactivex.Single

interface AppSharedPreferences {
    var covid19PtData: List<Map<String, String>>
    var covid19PtVaccination: List<Map<String, String>>
    var covid19PtDataTimeStamp: Long
    var covid19PtVaccinationTimeStamp: Long
    var nightMode: String
    var locale: String

    fun initialize(context: Context)

    fun getLocalCovid19PtDataSingle(): Single<List<Map<String, String>>>
    fun getLocalCovid19PtVaccinationSingle(): Single<List<Map<String, String>>>

    companion object {
        const val MODE_NIGHT_YES = "mode_night_yes"
        const val MODE_NIGHT_NO = "mode_night_no"
        const val LOCATE_EN = "en"
        const val LOCALE_PT = "pt"
    }
}
