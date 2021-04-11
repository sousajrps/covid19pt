package com.sousajrps.covid19pt.riskMatrix

import com.sousajrps.covid19pt.remote.RemoteData
import com.sousajrps.covid19pt.remote.mappers.DataMapper
import com.sousajrps.covid19pt.remote.models.Data
import com.sousajrps.covid19pt.sharedPreferences.AppSharedPreferences
import io.reactivex.Single

class MatrixRepositoryImpl(
    private val remoteData: RemoteData,
    private val appSharedPreferences: AppSharedPreferences,
    private val dataMapper: DataMapper
) : MatrixRepository {

    override fun getData(): Single<List<Data>> = remoteData.getRemoteCovid19PtData()
        .doOnSuccess { appSharedPreferences.covid19PtData = it }
        .onErrorResumeNext { appSharedPreferences.getLocalCovid19PtDataSingle() }
        .map { dataMapper.mapCovid19PtRawData(it) }

    override fun getLocalData(): Single<List<Data>> =
        appSharedPreferences.getLocalCovid19PtDataSingle()
            .map { dataMapper.mapCovid19PtRawData(it) }
}
