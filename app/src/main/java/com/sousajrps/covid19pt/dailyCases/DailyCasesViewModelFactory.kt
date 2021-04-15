package com.sousajrps.covid19pt.dailyCases

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sousajrps.covid19pt.AppModule
import com.sousajrps.covid19pt.scheduler.SchedulerModule

class DailyCasesViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DailyCasesViewModel::class.java)) {
            return DailyCasesViewModel(
                matrixRepository = AppModule.getMatrixRepository(),
                appSharedPreferences = AppModule.getAppSharedPreferences(),
                dataToConfirmedCasesMapper = AppModule.getDataToConfirmedCasesMapper(),
                remoteConfigUtils = AppModule.getRemoteConfigs(),
                schedulerProvider = SchedulerModule.schedulerProvider()
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: $modelClass")
    }
}
