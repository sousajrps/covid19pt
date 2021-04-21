package com.sousajrps.covid19pt.vaccination

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sousajrps.covid19pt.SingleLiveEvent
import com.sousajrps.covid19pt.remote.RemoteConfigUtils
import com.sousajrps.covid19pt.remote.models.Vaccination
import com.sousajrps.covid19pt.remote.models.VaccinationWeekly
import com.sousajrps.covid19pt.scheduler.SchedulerProvider
import com.sousajrps.covid19pt.sharedPreferences.AppSharedPreferences
import io.reactivex.disposables.CompositeDisposable

class VaccinationViewModel(
    private val vaccinationRepository: VaccinationRepository,
    private val appSharedPreferences: AppSharedPreferences,
    private val vaccinationTotalsMapper: VaccinationTotalsMapper,
    private val dataToVaccinationReportMapper: DataToVaccinationReportMapper,
    private val dataToVaccinationWeeklyMapper: DataToVaccinationWeeklyMapper,
    private val remoteConfigUtils: RemoteConfigUtils,
    private val schedulerProvider: SchedulerProvider
) : ViewModel() {
    private val TAG = "VaccinationViewModel"
    private var time: Long = 0
    private val compositeDisposable = CompositeDisposable()

    val data: LiveData<VaccinationUiModel> get() = dataM
    val showLoading: LiveData<Boolean> get() = showLoadingM

    private val dataM = SingleLiveEvent<VaccinationUiModel>()
    private val showLoadingM = SingleLiveEvent<Boolean>()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    fun getData(time: Long) {
        this.time = time
        showLoadingM.value = true
        if (shouldRefreshData()) {
            getAllRemoteData()
        } else {
            getAllLocalData()
        }
    }

    private fun shouldRefreshData() =
        time > (appSharedPreferences.covid19PtVaccinationTimeStamp + remoteConfigUtils.getAppConfigurations().timeOffset)

    private fun processResponse(chartData: VaccinationUiModel) {
        dataM.value = chartData

        showLoadingM.value = false
    }

    private fun getAllRemoteData() =
        vaccinationRepository.getAllRemoteData().toObservable()
            .observeOn(schedulerProvider.mainThread())
            .subscribeOn(schedulerProvider.backgroundThread())
            .map { mapToUiModel(it) }
            .subscribe(
                { response ->
                    appSharedPreferences.covid19PtVaccinationTimeStamp = time
                    processResponse(response)
                }, { error ->
                    Log.d(TAG, error.localizedMessage.orEmpty())
                }
            )
            .also { compositeDisposable.add(it) }

    private fun getAllLocalData() =
        vaccinationRepository.getAllRemoteData().toObservable()
            .observeOn(schedulerProvider.mainThread())
            .subscribeOn(schedulerProvider.backgroundThread())
            .map { mapToUiModel(it) }
            .subscribe(
                { response ->
                    processResponse(response)
                },
                { error ->
                    Log.d(TAG, error.localizedMessage.orEmpty())
                }
            )
            .also { compositeDisposable.add(it) }

    private fun mapToUiModel(pair: Pair<List<Vaccination>, List<VaccinationWeekly>>): VaccinationUiModel {
        val vaccinationLastDay = pair.first.last()
        val vaccinationLastWeek = pair.second.last()
        return VaccinationUiModel(
            vaccinationTotals = vaccinationTotalsMapper.map(
                vaccinationLastDay,
                vaccinationLastWeek.populacao1.toInt()
            ),
            vaccinationReportItem = dataToVaccinationReportMapper.getItems(vaccinationLastDay),
            vaccinationWeeklyUiModel = dataToVaccinationWeeklyMapper.getItems(vaccinationLastWeek),
            vaccinationChartUiModel = dataToVaccinationReportMapper.getVaccinationTotalsChartData(
                pair.first
            )
        )
    }
}
