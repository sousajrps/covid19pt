package com.sousajrps.covid19pt.vaccination

import com.sousajrps.covid19pt.remote.RemoteData
import com.sousajrps.covid19pt.remote.mappers.VaccinationMapper
import com.sousajrps.covid19pt.remote.mappers.VaccinationWeeklyMapper
import com.sousajrps.covid19pt.remote.models.Vaccination
import com.sousajrps.covid19pt.remote.models.VaccinationWeekly
import com.sousajrps.covid19pt.sharedPreferences.AppSharedPreferences
import io.reactivex.Single

class VaccinationRepositoryImpl(
    private val remoteData: RemoteData,
    private val appSharedPreferences: AppSharedPreferences,
    private val vaccinationMapper: VaccinationMapper,
    private val vaccinationWeeklyMapper: VaccinationWeeklyMapper
) : VaccinationRepository {

    override fun getAllRemoteData(): Single<Pair<List<Vaccination>, List<VaccinationWeekly>>> =
        Single.zip(
            getRemoteVaccination(),
            getRemoteVaccinationWeekly(),
            { first: List<Vaccination>,
              second: List<VaccinationWeekly> ->
                Pair(first, second)
            })

    override fun getAllLocalData(): Single<Pair<List<Vaccination>, List<VaccinationWeekly>>> =
        Single.zip(
            getLocalVaccination(),
            getLocalVaccinationWeekly(),
            { first: List<Vaccination>,
              second: List<VaccinationWeekly> ->
                Pair(first, second)
            })

    private fun getRemoteVaccination(): Single<List<Vaccination>> =
        remoteData.getRemoteCovid19PtVaccination()
            .doOnSuccess { appSharedPreferences.covid19PtVaccination = it }
            .onErrorResumeNext { appSharedPreferences.getLocalCovid19PtVaccinationSingle() }
            .map { vaccinationMapper.mapCovid19VaccinationRawData(it) }

    private fun getLocalVaccination(): Single<List<Vaccination>> =
        appSharedPreferences.getLocalCovid19PtVaccinationSingle()
            .map { vaccinationMapper.mapCovid19VaccinationRawData(it) }

    private fun getRemoteVaccinationWeekly(): Single<List<VaccinationWeekly>> =
        remoteData.getRemoteCovid19PtVaccinationWeekly()
            .doOnSuccess { appSharedPreferences.covid19PtVaccinationWeekly = it }
            .onErrorResumeNext { appSharedPreferences.getLocalCovid19PtVaccinationWeeklySingle() }
            .map { vaccinationWeeklyMapper.mapVaccinationWeeklyRawData(it) }

    private fun getLocalVaccinationWeekly(): Single<List<VaccinationWeekly>> =
        appSharedPreferences.getLocalCovid19PtVaccinationWeeklySingle()
            .map { vaccinationWeeklyMapper.mapVaccinationWeeklyRawData(it) }
}
