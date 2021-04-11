package com.sousajrps.covid19pt.riskMatrix

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sousajrps.covid19pt.SingleLiveEvent
import com.sousajrps.covid19pt.riskMatrix.models.RiskMatrix
import com.sousajrps.covid19pt.scheduler.SchedulerProvider
import com.sousajrps.covid19pt.sharedPreferences.AppSharedPreferences
import io.reactivex.disposables.CompositeDisposable

class RiskMatrixViewModel(
    private val matrixRepository: MatrixRepository,
    private val appSharedPreferences: AppSharedPreferences,
    private val dataToMatrixMapper: DataToMatrixMapper,
    private val schedulerProvider: SchedulerProvider
) : ViewModel() {
    private val TAG = "RiskMatrixViewModel"
    private var time: Long = 0
    private val compositeDisposable = CompositeDisposable()

    val data: LiveData<List<RiskMatrix>> get() = dataM
    val showLoading: LiveData<Boolean> get() = showLoadingM

    private val dataM = SingleLiveEvent<List<RiskMatrix>>()
    private val showLoadingM = SingleLiveEvent<Boolean>()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    fun getData(time: Long) {
        this.time = time
        showLoadingM.value = true
        if (shouldRefreshData()) {
            refreshData()
        } else {
            loadLocalData()
        }
    }

    private fun shouldRefreshData() = appSharedPreferences.covid19PtDataTimeStamp == 0L
            || time > (appSharedPreferences.covid19PtDataTimeStamp + TIME_OFFSET)

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
