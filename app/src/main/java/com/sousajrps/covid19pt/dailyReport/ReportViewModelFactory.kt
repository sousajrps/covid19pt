package com.sousajrps.covid19pt.dailyReport

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sousajrps.covid19pt.AppModule
import com.sousajrps.covid19pt.scheduler.SchedulerModule

class ReportViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ReportViewModel::class.java)) {
            return ReportViewModel(
                matrixRepository = AppModule.getMatrixRepository(),
                appSharedPreferences = AppModule.getAppSharedPreferences(),
                dataToDailyReportMapper = AppModule.getDataToDailyReportMapper(),
                dataToCustomChartDataValues = AppModule.getDataToCustomChartValuesMapper(),
                remoteConfigUtils = AppModule.getRemoteConfigs(),
                schedulerProvider = SchedulerModule.schedulerProvider()
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: $modelClass")
    }
}
