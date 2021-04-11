package com.sousajrps.covid19pt.vaccination

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sousajrps.covid19pt.SingleLiveEvent
import com.sousajrps.covid19pt.remote.models.Vaccination
import com.sousajrps.covid19pt.riskMatrix.TIME_OFFSET
import com.sousajrps.covid19pt.scheduler.SchedulerProvider
import com.sousajrps.covid19pt.sharedPreferences.AppSharedPreferences
import io.reactivex.disposables.CompositeDisposable

class VaccinationViewModel(
    private val vaccinationRepository: VaccinationRepository,
    private val appSharedPreferences: AppSharedPreferences,
    private val schedulerProvider: SchedulerProvider
) : ViewModel() {
    private val TAG = "VaccinationViewModel"
    private var time: Long = 0
    private val compositeDisposable = CompositeDisposable()

    val data: LiveData<Vaccination> get() = dataM
    val showLoading: LiveData<Boolean> get() = showLoadingM

    private val dataM = SingleLiveEvent<Vaccination>()
    private val showLoadingM = SingleLiveEvent<Boolean>()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    fun getData(time: Long) {
        this.time = time
        showLoadingM.value = true
        if (shouldRefreshData()) {
            Log.d("FFFFF", "refreshData")
            refreshData()
        } else {
            Log.d("FFFFF", "loadLocalData")
            loadLocalData()
        }
    }

    private fun shouldRefreshData() =
        time > (appSharedPreferences.covid19PtVaccinationTimeStamp + TIME_OFFSET)

    private fun refreshData() = vaccinationRepository.getRemoteVaccination()
        .observeOn(schedulerProvider.mainThread())
        .subscribeOn(schedulerProvider.backgroundThread())
        // .map { dataToConfirmedCasesMapper.map(rawData = it) }
        //  .map { it.takeLast(30) }
        .subscribe(
            { response ->
                appSharedPreferences.covid19PtVaccinationTimeStamp = time
                processResponse(response)
            }, { error -> Log.d(TAG, error.localizedMessage.orEmpty()) }
        )
        .also { compositeDisposable.add(it) }


    private fun loadLocalData() = vaccinationRepository.getLocalVaccination()
        .observeOn(schedulerProvider.mainThread())
        .subscribeOn(schedulerProvider.backgroundThread())
        // .map { dataToConfirmedCasesMapper.map(rawData = it) }
        //  .map { it.takeLast(30) }
        .subscribe(
            { response ->
                processResponse(response)
            }, { error -> Log.d(TAG, error.localizedMessage.orEmpty()) }
        )
        .also { compositeDisposable.add(it) }


    private fun processResponse(chartData: List<Vaccination>) {
        Log.d("FFFFF", "processResponse: ${chartData.last()}")
        dataM.value = chartData.last()
        showLoadingM.value = false
    }

}
