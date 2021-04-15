package com.sousajrps.covid19pt.riskMatrix

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sousajrps.covid19pt.remote.models.AppConfigurations
import com.sousajrps.covid19pt.remote.models.MatrixParameters
import com.sousajrps.covid19pt.remote.RemoteConfigUtils
import com.sousajrps.covid19pt.SingleLiveEvent
import com.sousajrps.covid19pt.riskMatrix.models.RiskMatrix
import com.sousajrps.covid19pt.scheduler.SchedulerProvider
import com.sousajrps.covid19pt.sharedPreferences.AppSharedPreferences
import io.reactivex.disposables.CompositeDisposable

class RiskMatrixViewModel(
    private val matrixRepository: MatrixRepository,
    private val appSharedPreferences: AppSharedPreferences,
    private val dataToMatrixMapper: DataToMatrixMapper,
    private val remoteConfigUtils: RemoteConfigUtils,
    private val schedulerProvider: SchedulerProvider
) : ViewModel() {
    private val TAG = "RiskMatrixViewModel"
    private var time: Long = 0
    private val compositeDisposable = CompositeDisposable()
    private var appConfigurations = AppConfigurations()

    val data: LiveData<List<RiskMatrix>> get() = dataM
    val matrixParameters: LiveData<MatrixParameters> get() = matrixParametersM
    val showLoading: LiveData<Boolean> get() = showLoadingM

    private val dataM = SingleLiveEvent<List<RiskMatrix>>()
    private val matrixParametersM = SingleLiveEvent<MatrixParameters>()
    private val showLoadingM = SingleLiveEvent<Boolean>()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    fun initViews() {
        appConfigurations = remoteConfigUtils.getAppConfigurations()
        matrixParametersM.value = appConfigurations.matrixParameters
    }

    fun getData(time: Long) {
        showLoadingM.value = true
        this.time = time
        if (shouldRefreshData()) {
            refreshData()
        } else {
            loadLocalData()
        }
    }

    private fun shouldRefreshData() = appSharedPreferences.covid19PtDataTimeStamp == 0L
            || time > (appSharedPreferences.covid19PtDataTimeStamp + appConfigurations.timeOffset)

    private fun refreshData() = matrixRepository.getData()
        .observeOn(schedulerProvider.mainThread())
        .subscribeOn(schedulerProvider.backgroundThread())
        .map { dataToMatrixMapper.map(rawData = it) }
        .subscribe(
            { response ->
                appSharedPreferences.covid19PtDataTimeStamp = time
                processResponse(response)
            }, { error -> Log.d(TAG, error.localizedMessage.orEmpty()) }
        )
        .also { compositeDisposable.add(it) }


    private fun loadLocalData() = matrixRepository.getLocalData()
        .observeOn(schedulerProvider.mainThread())
        .subscribeOn(schedulerProvider.backgroundThread())
        .map { dataToMatrixMapper.map(rawData = it) }
        .subscribe(
            { response ->
                processResponse(response)
            }, { error -> Log.d(TAG, error.localizedMessage.orEmpty()) }
        )
        .also { compositeDisposable.add(it) }


    private fun processResponse(chartData: List<RiskMatrix>) {
        dataM.value = chartData
        showLoadingM.value = false
    }
}
