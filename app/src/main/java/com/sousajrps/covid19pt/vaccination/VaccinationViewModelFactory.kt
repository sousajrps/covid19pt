package com.sousajrps.covid19pt.vaccination

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sousajrps.covid19pt.AppModule
import com.sousajrps.covid19pt.scheduler.SchedulerModule

class VaccinationViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VaccinationViewModel::class.java)) {
            return VaccinationViewModel(
                vaccinationRepository = AppModule.getVaccinationRepository(),
                appSharedPreferences = AppModule.getAppSharedPreferences(),
                vaccinationTotalsMapper = AppModule.getVaccinationTotalsMapper(),
                dataToVaccinationReportMapper = AppModule.getDataToVaccinationReportMapper(),
                remoteConfigUtils = AppModule.getRemoteConfigs(),
                schedulerProvider = SchedulerModule.schedulerProvider()
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: $modelClass")
    }
}
