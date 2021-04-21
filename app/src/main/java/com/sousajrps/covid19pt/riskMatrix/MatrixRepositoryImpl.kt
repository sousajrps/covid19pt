package com.sousajrps.covid19pt.riskMatrix

import com.sousajrps.covid19pt.AppModule
import com.sousajrps.covid19pt.local.Data
import com.sousajrps.covid19pt.local.DataDao
import com.sousajrps.covid19pt.remote.RemoteData
import com.sousajrps.covid19pt.remote.mappers.DataMapper
import com.sousajrps.covid19pt.sharedPreferences.AppSharedPreferences
import io.reactivex.Single

class MatrixRepositoryImpl(
    private val remoteData: RemoteData,
    private val appSharedPreferences: AppSharedPreferences,
    private val dataMapper: DataMapper,
    private val dataDao: DataDao
) : MatrixRepository, BaseRepository(
    appSharedPreferences = appSharedPreferences,
    appConfigurations = AppModule.getRemoteConfigs().getAppConfigurations()
) {

    override fun getData(time: Long): Single<List<Data>> =
        if (shouldRefreshData(time = time, TimeStamp.DATA)) {
            getRemoteData(time)
        } else {
            getLocalData()
        }

    private fun getRemoteData(time: Long): Single<List<Data>> =
        remoteData.getRemoteCovid19PtData()
            .map { dataMapper.mapCovid19PtRawData(it) }
            .doOnSuccess {
                dataDao.insertAll(it)
                appSharedPreferences.dataTimeStamp = time
            }
            .onErrorResumeNext { dataDao.getAll() }

    private fun getLocalData() = dataDao.getAll()

}
