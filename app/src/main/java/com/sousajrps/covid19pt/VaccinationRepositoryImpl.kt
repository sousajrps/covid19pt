package com.sousajrps.covid19pt

import android.util.Log
import com.sousajrps.covid19pt.remote.RemoteData
import com.sousajrps.covid19pt.remote.mappers.VaccinationMapper
import com.sousajrps.covid19pt.remote.models.Vaccination
import com.sousajrps.covid19pt.sharedPreferences.AppSharedPreferences
import io.reactivex.Single

class VaccinationRepositoryImpl(
    private val remoteData: RemoteData,
    private val appSharedPreferences: AppSharedPreferences,
    private val vaccinationMapper: VaccinationMapper
) : VaccinationRepository {

    override fun getRemoteVaccination(): Single<List<Vaccination>> =
        remoteData.getRemoteCovid19PtVaccination()
            .doOnSuccess { appSharedPreferences.covid19PtVaccination = it }
            .onErrorResumeNext { appSharedPreferences.getLocalCovid19PtVaccinationSingle() }
            .map { vaccinationMapper.mapCovid19VaccinationRawData(it) }

    override fun getLocalVaccination(): Single<List<Vaccination>> =
        appSharedPreferences.getLocalCovid19PtVaccinationSingle()
            .map { vaccinationMapper.mapCovid19VaccinationRawData(it) }
}
