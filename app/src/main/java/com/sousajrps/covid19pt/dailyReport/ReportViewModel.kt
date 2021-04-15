package com.sousajrps.covid19pt.dailyReport

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sousajrps.covid19pt.remote.RemoteConfigUtils
import com.sousajrps.covid19pt.SingleLiveEvent
import com.sousajrps.covid19pt.dailyCases.DailyCases
import com.sousajrps.covid19pt.dailyCases.DataToDailyCasesMapper
import com.sousajrps.covid19pt.riskMatrix.MatrixRepository
import com.sousajrps.covid19pt.scheduler.SchedulerProvider
import com.sousajrps.covid19pt.sharedPreferences.AppSharedPreferences
import io.reactivex.disposables.CompositeDisposable

class ReportViewModel(
    private val matrixRepository: MatrixRepository,
    private val appSharedPreferences: AppSharedPreferences,
    private val dataToDailyReportMapper: DataToDailyReportMapper,
    private val dataToDailyCasesMapper: DataToDailyCasesMapper,
    private val remoteConfigUtils: RemoteConfigUtils,
    private val schedulerProvider: SchedulerProvider
) : ViewModel() {
    private val TAG = "ReportViewModel"
    private var time: Long = 0
    private val compositeDisposable = CompositeDisposable()

    val data: LiveData<List<DailyReportItem>> get() = dataM
    val dailyCases: LiveData<List<DailyCases>> get() = dailyCasesM
    val showLoading: LiveData<Boolean> get() = showLoadingM

    private val dataM = SingleLiveEvent<List<DailyReportItem>>()
    private val dailyCasesM = SingleLiveEvent<List<DailyCases>>()
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
            || time > (appSharedPreferences.covid19PtDataTimeStamp + remoteConfigUtils.getAppConfigurations().timeOffset)

    private fun refreshData() = matrixRepository.getData()
        .observeOn(schedulerProvider.mainThread())
        .subscribeOn(schedulerProvider.backgroundThread())
        .map { Pair(dataToDailyReportMapper.getReportItems(rawData = it), dataToDailyCasesMapper.map(it)) }
      //  .map { it.takeLast(30) }
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
        .map { Pair(dataToDailyReportMapper.getReportItems(rawData = it), dataToDailyCasesMapper.map(it)) }
      //  .map { it.takeLast(30) }
        .subscribe(
            { response ->
                processResponse(response)
            }, { error -> Log.d(TAG, error.localizedMessage.orEmpty()) }
        )
        .also { compositeDisposable.add(it) }


    private fun processResponse(chartData: Pair<List<DailyReportItem>, List<DailyCases>>) {
        dataM.value = chartData.first
        dailyCasesM.value = chartData.second
        showLoadingM.value = false
    }

}
