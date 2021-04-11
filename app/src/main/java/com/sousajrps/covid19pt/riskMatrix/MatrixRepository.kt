package com.sousajrps.covid19pt.riskMatrix

import com.sousajrps.covid19pt.remote.models.Data
import io.reactivex.Single

interface MatrixRepository {
    fun getData(): Single<List<Data>>
    fun getLocalData(): Single<List<Data>>
}
