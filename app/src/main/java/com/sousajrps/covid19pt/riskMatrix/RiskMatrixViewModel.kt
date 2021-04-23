package com.sousajrps.covid19pt.riskMatrix

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sousajrps.covid19pt.SingleLiveEvent
import com.sousajrps.covid19pt.remote.RemoteConfigUtils
import com.sousajrps.covid19pt.remote.models.AppConfigurations
import com.sousajrps.covid19pt.remote.models.MatrixParameters
import com.sousajrps.covid19pt.riskMatrix.models.RiskMatrix
import com.sousajrps.covid19pt.scheduler.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class RiskMatrixViewModel(
    private val matrixRepository: MatrixRepository,
    private val dataToMatrixMapper: DataToMatrixMapper,
    private val remoteConfigUtils: RemoteConfigUtils,
    private val schedulerProvider: SchedulerProvider
) : ViewModel() {
    private val TAG = "RiskMatrixViewModel"
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

    fun getData(time: Long) = matrixRepository.getData(time)
        .observeOn(schedulerProvider.mainThread())
        .subscribeOn(schedulerProvider.backgroundThread())
        .doOnSubscribe { showLoadingM.value = true }
        .doAfterTerminate { showLoadingM.value = false }
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
