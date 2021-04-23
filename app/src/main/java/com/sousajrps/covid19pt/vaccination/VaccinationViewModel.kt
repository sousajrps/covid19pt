package com.sousajrps.covid19pt.vaccination

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sousajrps.covid19pt.SingleLiveEvent
import com.sousajrps.covid19pt.local.Vaccination
import com.sousajrps.covid19pt.local.VaccinationWeekly
import com.sousajrps.covid19pt.scheduler.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class VaccinationViewModel(
    private val vaccinationRepository: VaccinationRepository,
    private val vaccinationTotalsMapper: VaccinationTotalsMapper,
    private val dataToVaccinationReportMapper: DataToVaccinationReportMapper,
    private val dataToVaccinationWeeklyMapper: DataToVaccinationWeeklyMapper,
    private val schedulerProvider: SchedulerProvider
) : ViewModel() {
    private val TAG = "VaccinationViewModel"
    private val compositeDisposable = CompositeDisposable()

    val data: LiveData<VaccinationUiModel> get() = dataM
    val showLoading: LiveData<Boolean> get() = showLoadingM

    private val dataM = SingleLiveEvent<VaccinationUiModel>()
    private val showLoadingM = SingleLiveEvent<Boolean>()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    private fun processResponse(chartData: VaccinationUiModel) {
        dataM.value = chartData
        showLoadingM.value = false
    }

    fun getData(time: Long) = vaccinationRepository.getVaccinationData(time)
            .observeOn(schedulerProvider.mainThread())
            .subscribeOn(schedulerProvider.backgroundThread())
            .doOnSubscribe { showLoadingM.value = true }
            .doAfterTerminate { showLoadingM.value = false }
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
