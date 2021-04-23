package com.sousajrps.covid19pt

import com.sousajrps.covid19pt.remote.models.AppConfigurations
import com.sousajrps.covid19pt.sharedPreferences.AppSharedPreferences

open class BaseRepository(
    private val appConfigurations: AppConfigurations,
    private val appSharedPreferences: AppSharedPreferences
) {

    fun shouldRefreshData(time: Long, timeStamp: TimeStamp): Boolean {
        val lastTimeStamp = getLastTimeStamp(timeStamp)
        return lastTimeStamp == 0L || time > lastTimeStamp + appConfigurations.timeOffset
    }

    private fun getLastTimeStamp(timeStamp: TimeStamp) =
        when (timeStamp) {
            TimeStamp.DATA -> appSharedPreferences.dataTimeStamp
            TimeStamp.VACCINATION -> appSharedPreferences.vaccinationTimeStamp
            TimeStamp.VACCINATION_WEEKLY -> appSharedPreferences.vaccinationWeeklyTimeStamp
        }
}

enum class TimeStamp {
    DATA,
    VACCINATION,
    VACCINATION_WEEKLY
}
