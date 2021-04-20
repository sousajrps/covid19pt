package com.sousajrps.covid19pt.dailyReport

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sousajrps.covid19pt.R
import com.sousajrps.covid19pt.SingleLiveEvent
import com.sousajrps.covid19pt.CustomChart.CustomChartData
import com.sousajrps.covid19pt.CustomChart.CustomChartDataSet
import com.sousajrps.covid19pt.remote.RemoteConfigUtils
import com.sousajrps.covid19pt.remote.models.Data
import com.sousajrps.covid19pt.riskMatrix.MatrixRepository
import com.sousajrps.covid19pt.scheduler.SchedulerProvider
import com.sousajrps.covid19pt.sharedPreferences.AppSharedPreferences
import io.reactivex.disposables.CompositeDisposable

class ReportViewModel(
    private val matrixRepository: MatrixRepository,
    private val appSharedPreferences: AppSharedPreferences,
    private val dataToDailyReportMapper: DataToDailyReportMapper,
    private val dataToCustomChartDataValues: DataToCustomChartDataValues,
    private val remoteConfigUtils: RemoteConfigUtils,
    private val schedulerProvider: SchedulerProvider
) : ViewModel() {
    private val TAG = "ReportViewModel"
    private var time: Long = 0
    private val compositeDisposable = CompositeDisposable()

    val dailyReport: LiveData<List<DailyReportItem>> get() = dailyReportM
    val dailyCases: LiveData<CustomChartData> get() = dailyCasesM
    val hospitalized: LiveData<CustomChartData> get() = hospitalizedM
    val totals: LiveData<CustomChartData> get() = totalsM
    val showLoading: LiveData<Boolean> get() = showLoadingM

    private val dailyReportM = SingleLiveEvent<List<DailyReportItem>>()
    private val dailyCasesM = SingleLiveEvent<CustomChartData>()
    private val hospitalizedM = SingleLiveEvent<CustomChartData>()
    private val totalsM = SingleLiveEvent<CustomChartData>()
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
        .map {
            mapResponse(it)
        }
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
        .map { mapResponse(it)
        }
        .subscribe(
            { response ->
                processResponse(response)
            }, { error -> Log.d(TAG, error.localizedMessage.orEmpty()) }
        )
        .also { compositeDisposable.add(it) }


    private fun processResponse(response: DailyReportUiModel) {
        dailyReportM.value = response.report
        dailyCasesM.value = CustomChartData(
            title = R.string.daily_cases_title,
            sets = listOf(
                CustomChartDataSet(
                    label = R.string.daily_cases_title,
                    colorLines = R.color.chartLines,
                    colorCircles = R.color.chartCircles,
                    dailyCases = response.dailyCases
                )
            )
        )
        hospitalizedM.value = CustomChartData(
            title = R.string.report_hospitalized,
            sets = listOf(
                CustomChartDataSet(
                    label = R.string.report_hospitalized,
                    colorLines = R.color.chartLines,
                    colorCircles = R.color.chartCircles,
                    dailyCases = response.hospitalized
                ),CustomChartDataSet(
                    label = R.string.report_hospitalized_icu,
                    colorLines = R.color.red,
                    colorCircles = R.color.red,
                    dailyCases = response.hospitalizedIcu
                )
            )

        )

        totalsM.value = CustomChartData(
            title = R.string.report_total,
            sets = listOf(
                CustomChartDataSet(
                    label = R.string.report_recovered,
                    colorLines = R.color.green,
                    colorCircles = R.color.green,
                    dailyCases = response.recovered
                ),
                CustomChartDataSet(
                    label = R.string.report_active,
                    colorLines = R.color.yellow,
                    colorCircles = R.color.yellow,
                    dailyCases = response.active
                ),
                CustomChartDataSet(
                    label = R.string.report_deaths,
                    colorLines = R.color.red,
                    colorCircles = R.color.red,
                    dailyCases = response.deaths
                )
            )

        )
        showLoadingM.value = false
    }

    private fun mapResponse(list: List<Data>):DailyReportUiModel{
        return DailyReportUiModel(
            report = dataToDailyReportMapper.getReportItems(rawData = list),
            dailyCases =  dataToCustomChartDataValues.mapDailyCases(list),
            hospitalized = dataToCustomChartDataValues.mapHospitalized(list),
            hospitalizedIcu = dataToCustomChartDataValues.mapHospitalizedIcu(list),
            recovered = dataToCustomChartDataValues.mapRecovered(list),
            active = dataToCustomChartDataValues.mapActive(list),
            deaths = dataToCustomChartDataValues.mapDeath(list)
        )

    }

}
