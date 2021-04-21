package com.sousajrps.covid19pt.vaccination

import com.sousajrps.covid19pt.AppModule
import com.sousajrps.covid19pt.local.Vaccination
import com.sousajrps.covid19pt.local.VaccinationDao
import com.sousajrps.covid19pt.local.VaccinationWeekly
import com.sousajrps.covid19pt.local.VaccinationWeeklyDao
import com.sousajrps.covid19pt.remote.RemoteData
import com.sousajrps.covid19pt.remote.mappers.VaccinationMapper
import com.sousajrps.covid19pt.remote.mappers.VaccinationWeeklyMapper
import com.sousajrps.covid19pt.riskMatrix.BaseRepository
import com.sousajrps.covid19pt.riskMatrix.TimeStamp
import com.sousajrps.covid19pt.sharedPreferences.AppSharedPreferences
import io.reactivex.Single

class VaccinationRepositoryImpl(
    private val remoteData: RemoteData,
    private val appSharedPreferences: AppSharedPreferences,
    private val vaccinationDao: VaccinationDao,
    private val vaccinationWeeklyDao: VaccinationWeeklyDao,
    private val vaccinationMapper: VaccinationMapper,
    private val vaccinationWeeklyMapper: VaccinationWeeklyMapper
) : VaccinationRepository, BaseRepository(
    appSharedPreferences = appSharedPreferences,
    appConfigurations = AppModule.getRemoteConfigs().getAppConfigurations()
) {

    override fun getVaccinationData(time: Long): Single<Pair<List<Vaccination>, List<VaccinationWeekly>>> =
        Single.zip(
            getVaccinationDailyReport(time),
            getVaccinationWeeklyReport(time),
            { first: List<Vaccination>,
              second: List<VaccinationWeekly> ->
                Pair(first, second)
            })

    private fun getVaccinationDailyReport(time: Long): Single<List<Vaccination>> =
        if (shouldRefreshData(time = time, timeStamp = TimeStamp.VACCINATION)) {
            getRemoteVaccination(time)
        } else {
            getLocalVaccination()
        }

    private fun getRemoteVaccination(time: Long): Single<List<Vaccination>> =
        remoteData.getRemoteCovid19PtVaccination()
            .map { vaccinationMapper.mapCovid19VaccinationRawData(it) }
            .doOnSuccess {
                appSharedPreferences.vaccinationTimeStamp = time
                vaccinationDao.insertAll(it)
            }
            .onErrorResumeNext { vaccinationDao.getAll() }

    private fun getLocalVaccination(): Single<List<Vaccination>> =
        vaccinationDao.getAll()

    private fun getVaccinationWeeklyReport(time: Long): Single<List<VaccinationWeekly>> =
        if (shouldRefreshData(time = time, timeStamp = TimeStamp.VACCINATION_WEEKLY)) {
            getRemoteVaccinationWeekly(time)
        } else {
            getLocalVaccinationWeekly()
        }

    private fun getRemoteVaccinationWeekly(time: Long): Single<List<VaccinationWeekly>> =
        remoteData.getRemoteCovid19PtVaccinationWeekly()
            .map { vaccinationWeeklyMapper.mapVaccinationWeeklyRawData(it) }
            .doOnSuccess {
                appSharedPreferences.vaccinationWeeklyTimeStamp = time
                vaccinationWeeklyDao.insertAll(it)
            }
            .onErrorResumeNext { vaccinationWeeklyDao.getAll() }

    private fun getLocalVaccinationWeekly(): Single<List<VaccinationWeekly>> =
        vaccinationWeeklyDao.getAll()
}
