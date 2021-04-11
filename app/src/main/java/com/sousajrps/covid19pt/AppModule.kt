package com.sousajrps.covid19pt

import com.sousajrps.covid19pt.dailyReport.DataToDailyReportMapper
import com.sousajrps.covid19pt.dailyCases.DataToDailyCasesMapper
import com.sousajrps.covid19pt.riskMatrix.DataToMatrixMapper
import com.sousajrps.covid19pt.riskMatrix.MatrixRepository
import com.sousajrps.covid19pt.riskMatrix.MatrixRepositoryImpl
import com.sousajrps.covid19pt.remote.RemoteData
import com.sousajrps.covid19pt.remote.RemoteDataImpl
import com.sousajrps.covid19pt.remote.mappers.DataMapper
import com.sousajrps.covid19pt.remote.mappers.VaccinationMapper
import com.sousajrps.covid19pt.sharedPreferences.AppSharedPreferences
import com.sousajrps.covid19pt.sharedPreferences.AppSharedPreferencesModule

object AppModule {
    private val remoteData: RemoteData by lazy { RemoteDataImpl() }
    private val dataMapper: DataMapper by lazy { DataMapper }
    private val vaccinationMapper: VaccinationMapper by lazy { VaccinationMapper }
    private val dataToMatrixMapperImpl: DataToMatrixMapper by lazy { DataToMatrixMapper }
    private val dataToDailyReportMapperImpl: DataToDailyReportMapper by lazy { DataToDailyReportMapper }
    private val dataToConfirmedCasesMapperImpl: DataToDailyCasesMapper by lazy { DataToDailyCasesMapper }

    fun getAppSharedPreferences(): AppSharedPreferences =
        AppSharedPreferencesModule.getAppSharedPreferences()

    fun getMatrixRepository(): MatrixRepository = MatrixRepositoryImpl(
        remoteData = remoteData,
        dataMapper = dataMapper,
        appSharedPreferences = getAppSharedPreferences()
    )

    fun getVaccinationRepository() :VaccinationRepository = VaccinationRepositoryImpl(
        remoteData = remoteData,
        vaccinationMapper = vaccinationMapper,
        appSharedPreferences = getAppSharedPreferences()
    )

    fun getDataToMatrixMapper() = dataToMatrixMapperImpl
    fun getDataToConfirmedCasesMapper() = dataToConfirmedCasesMapperImpl
    fun getDataToDailyReportMapper() = dataToDailyReportMapperImpl
}
