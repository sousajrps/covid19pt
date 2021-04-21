package com.sousajrps.covid19pt.riskMatrix

import com.sousajrps.covid19pt.local.Data
import io.reactivex.Single

interface MatrixRepository {
    fun getData(time: Long): Single<List<Data>>
}
