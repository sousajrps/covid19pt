package com.sousajrps.covid19pt

import com.sousajrps.covid19pt.dailyCases.DataToDailyCasesMapper
import com.sousajrps.covid19pt.dailyReport.DataToDailyReportMapper
import com.sousajrps.covid19pt.remote.RemoteConfigUtils
import com.sousajrps.covid19pt.remote.RemoteConfigUtilsImpl
import com.sousajrps.covid19pt.remote.RemoteData
import com.sousajrps.covid19pt.remote.RemoteDataImpl
import com.sousajrps.covid19pt.remote.mappers.DataMapper
import com.sousajrps.covid19pt.remote.mappers.VaccinationMapper
import com.sousajrps.covid19pt.riskMatrix.DataToMatrixMapper
import com.sousajrps.covid19pt.riskMatrix.MatrixRepository
import com.sousajrps.covid19pt.riskMatrix.MatrixRepositoryImpl
import com.sousajrps.covid19pt.sharedPreferences.AppSharedPreferences
import com.sousajrps.covid19pt.sharedPreferences.AppSharedPreferencesModule
import com.sousajrps.covid19pt.vaccination.DataToVaccinationReportMapper
import com.sousajrps.covid19pt.vaccination.VaccinationRepository
import com.sousajrps.covid19pt.vaccination.VaccinationRepositoryImpl
import com.sousajrps.covid19pt.vaccination.VaccinationTotalsMapper

object AppModule {
    private val remoteData: RemoteData by lazy { RemoteDataImpl() }
    private val dataMapper: DataMapper by lazy { DataMapper }
    private val vaccinationMapper: VaccinationMapper by lazy { VaccinationMapper }
    private val dataToMatrixMapperImpl: DataToMatrixMapper by lazy { DataToMatrixMapper }
    private val dataToDailyReportMapperImpl: DataToDailyReportMapper by lazy { DataToDailyReportMapper }
    private val dataToConfirmedCasesMapperImpl: DataToDailyCasesMapper by lazy { DataToDailyCasesMapper }
    private val vaccinationTotalsMapperImpl: VaccinationTotalsMapper by lazy { VaccinationTotalsMapper }
    private val dataToVaccinationReportMapperImpl: DataToVaccinationReportMapper by lazy { DataToVaccinationReportMapper }
    private val RemoteConfigUtilsImpl: RemoteConfigUtils by lazy { RemoteConfigUtilsImpl() }

    fun getAppSharedPreferences(): AppSharedPreferences =
        AppSharedPreferencesModule.getAppSharedPreferences()

    fun getMatrixRepository(): MatrixRepository = MatrixRepositoryImpl(
        remoteData = remoteData,
        dataMapper = dataMapper,
        appSharedPreferences = getAppSharedPreferences()
    )

    fun getVaccinationRepository(): VaccinationRepository = VaccinationRepositoryImpl(
        remoteData = remoteData,
        vaccinationMapper = vaccinationMapper,
        appSharedPreferences = getAppSharedPreferences()
    )

    fun getDataToMatrixMapper(): DataToMatrixMapper = dataToMatrixMapperImpl
    fun getDataToConfirmedCasesMapper(): DataToDailyCasesMapper = dataToConfirmedCasesMapperImpl
    fun getDataToDailyReportMapper(): DataToDailyReportMapper = dataToDailyReportMapperImpl
    fun getVaccinationTotalsMapper(): VaccinationTotalsMapper = vaccinationTotalsMapperImpl
    fun getDataToVaccinationReportMapper(): DataToVaccinationReportMapper =
        dataToVaccinationReportMapperImpl
    fun getRemoteConfigs(): RemoteConfigUtils = RemoteConfigUtilsImpl
}
