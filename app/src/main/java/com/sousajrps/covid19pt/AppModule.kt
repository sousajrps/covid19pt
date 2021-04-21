package com.sousajrps.covid19pt

import com.sousajrps.covid19pt.dailyReport.DataToCustomChartDataValues
import com.sousajrps.covid19pt.dailyReport.DataToDailyReportMapper
import com.sousajrps.covid19pt.local.LocalModule
import com.sousajrps.covid19pt.remote.RemoteConfigUtils
import com.sousajrps.covid19pt.remote.RemoteConfigUtilsImpl
import com.sousajrps.covid19pt.remote.RemoteData
import com.sousajrps.covid19pt.remote.RemoteDataImpl
import com.sousajrps.covid19pt.remote.mappers.DataMapper
import com.sousajrps.covid19pt.remote.mappers.VaccinationMapper
import com.sousajrps.covid19pt.remote.mappers.VaccinationWeeklyMapper
import com.sousajrps.covid19pt.riskMatrix.DataToMatrixMapper
import com.sousajrps.covid19pt.riskMatrix.MatrixRepository
import com.sousajrps.covid19pt.riskMatrix.MatrixRepositoryImpl
import com.sousajrps.covid19pt.sharedPreferences.AppSharedPreferences
import com.sousajrps.covid19pt.sharedPreferences.AppSharedPreferencesModule
import com.sousajrps.covid19pt.vaccination.*

object AppModule {
    private val remoteData: RemoteData by lazy { RemoteDataImpl() }
    private val dataMapper: DataMapper by lazy { DataMapper }
    private val vaccinationMapper: VaccinationMapper by lazy { VaccinationMapper }
    private val vaccinationWeeklyMapper: VaccinationWeeklyMapper by lazy { VaccinationWeeklyMapper }
    private val dataToMatrixMapperImpl: DataToMatrixMapper by lazy { DataToMatrixMapper }
    private val dataToDailyReportMapperImpl: DataToDailyReportMapper by lazy { DataToDailyReportMapper }
    private val dataToCustomChartDataValuesImpl: DataToCustomChartDataValues by lazy { DataToCustomChartDataValues }
    private val vaccinationTotalsMapperImpl: VaccinationTotalsMapper by lazy { VaccinationTotalsMapper }
    private val dataToVaccinationReportMapperImpl: DataToVaccinationReportMapper by lazy { DataToVaccinationReportMapper }
    private val dataToVaccinationWeeklyMapperImpl: DataToVaccinationWeeklyMapper by lazy { DataToVaccinationWeeklyMapper }
    private val RemoteConfigUtilsImpl: RemoteConfigUtils by lazy { RemoteConfigUtilsImpl() }

    fun getAppSharedPreferences(): AppSharedPreferences =
        AppSharedPreferencesModule.getAppSharedPreferences()

    fun getMatrixRepository(): MatrixRepository = MatrixRepositoryImpl(
        remoteData = remoteData,
        dataMapper = dataMapper,
        appSharedPreferences = getAppSharedPreferences(),
        dataDao = LocalModule.getDataDao()
    )

    fun getVaccinationRepository(): VaccinationRepository = VaccinationRepositoryImpl(
        remoteData = remoteData,
        vaccinationMapper = vaccinationMapper,
        vaccinationWeeklyMapper = vaccinationWeeklyMapper,
        vaccinationDao = LocalModule.getVaccinationDao(),
        vaccinationWeeklyDao = LocalModule.getVaccinationWeeklyDao(),
        appSharedPreferences = getAppSharedPreferences()
    )

    fun getDataToMatrixMapper(): DataToMatrixMapper = dataToMatrixMapperImpl

    fun getDataToCustomChartValuesMapper(): DataToCustomChartDataValues =
        dataToCustomChartDataValuesImpl

    fun getDataToDailyReportMapper(): DataToDailyReportMapper = dataToDailyReportMapperImpl

    fun getVaccinationTotalsMapper(): VaccinationTotalsMapper = vaccinationTotalsMapperImpl

    fun getDataToVaccinationReportMapper(): DataToVaccinationReportMapper =
        dataToVaccinationReportMapperImpl

    fun getDataToVaccinationWeeklyMapper(): DataToVaccinationWeeklyMapper =
        dataToVaccinationWeeklyMapperImpl

    fun getRemoteConfigs(): RemoteConfigUtils = RemoteConfigUtilsImpl
}
