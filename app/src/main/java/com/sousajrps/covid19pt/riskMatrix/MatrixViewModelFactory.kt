package com.sousajrps.covid19pt.riskMatrix

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sousajrps.covid19pt.AppModule
import com.sousajrps.covid19pt.scheduler.SchedulerModule

class MatrixViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RiskMatrixViewModel::class.java)) {
            return RiskMatrixViewModel(
                matrixRepository = AppModule.getMatrixRepository(),
                dataToMatrixMapper = AppModule.getDataToMatrixMapper(),
                remoteConfigUtils = AppModule.getRemoteConfigs(),
                schedulerProvider = SchedulerModule.schedulerProvider()
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: $modelClass")
    }
}
