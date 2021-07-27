package com.sousajrps.covid19pt.dailyReport

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sousajrps.covid19pt.R
import com.sousajrps.covid19pt.SingleLiveEvent
import com.sousajrps.covid19pt.lineChartView.CustomChartData
import com.sousajrps.covid19pt.lineChartView.CustomChartDataSet
import com.sousajrps.covid19pt.local.Data
import com.sousajrps.covid19pt.riskMatrix.MatrixRepository
import com.sousajrps.covid19pt.scheduler.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class ReportViewModel(
    private val matrixRepository: MatrixRepository,
    private val dataToDailyReportMapper: DataToDailyReportMapper,
    private val dataToCustomChartDataValues: DataToCustomChartDataValues,
    private val schedulerProvider: SchedulerProvider
) : ViewModel() {
    private val TAG = "ReportViewModel"
    private val compositeDisposable = CompositeDisposable()

    val dailyReport: LiveData<DailyReportUiModel> get() = dailyReportM
    val showLoading: LiveData<Boolean> get() = showLoadingM

    private val dailyReportM = SingleLiveEvent<DailyReportUiModel>()
    private val showLoadingM = SingleLiveEvent<Boolean>()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    fun getData(time: Long) = matrixRepository.getData(time)
        .observeOn(schedulerProvider.mainThread())
        .subscribeOn(schedulerProvider.backgroundThread())
        .doOnSubscribe { showLoadingM.value = true }
        .doAfterTerminate { showLoadingM.value = false }
        .map { mapResponse(it) }
        .subscribe(
            { response -> dailyReportM.value = response
            }, { error -> Log.d(TAG, error.localizedMessage.orEmpty()) }
        )
        .also { compositeDisposable.add(it) }

    private fun mapResponse(list: List<Data>): DailyReportUiModel {
        return DailyReportUiModel(
            report = dataToDailyReportMapper.getReportItems(rawData = list),
            dailyCases = CustomChartData(
                title = R.string.daily_cases_title,
                sets = listOf(
                    CustomChartDataSet(
                        label = R.string.daily_cases_title,
                        colorLines = R.color.chartLines,
                        colorCircles = R.color.chartCircles,
                        dailyCases = dataToCustomChartDataValues.mapDailyCases(list)
                    )
                )
            ),
            hospitalized = CustomChartData(
                title = R.string.report_hospitalizes_evolution_title,
                sets = listOf(
                    CustomChartDataSet(
                        label = R.string.report_hospitalized,
                        colorLines = R.color.chartLines,
                        colorCircles = R.color.chartCircles,
                        dailyCases = dataToCustomChartDataValues.mapHospitalized(list)
                    ), CustomChartDataSet(
                        label = R.string.report_hospitalized_icu,
                        colorLines = R.color.red,
                        colorCircles = R.color.red,
                        dailyCases = dataToCustomChartDataValues.mapHospitalizedIcu(list)
                    )
                )
            ),
            totalCases = CustomChartData(
                title = R.string.report_total_cases_evolution_title,
                sets = listOf(
                    CustomChartDataSet(
                        label = R.string.report_active,
                        colorLines = R.color.yellow,
                        colorCircles = R.color.yellow,
                        dailyCases = dataToCustomChartDataValues.mapActive(list)
                    ),
                    CustomChartDataSet(
                        label = R.string.report_deaths,
                        colorLines = R.color.red,
                        colorCircles = R.color.red,
                        dailyCases = dataToCustomChartDataValues.mapDeath(list)
                    )
                )
            )
        )
    }
}
