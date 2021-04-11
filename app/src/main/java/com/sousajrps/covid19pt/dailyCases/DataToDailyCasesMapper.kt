package com.sousajrps.covid19pt.dailyCases

import com.sousajrps.covid19pt.remote.models.Data

object DataToDailyCasesMapper {
    fun map(
        rawData: List<Data>
    ): List<DailyCases> =
        rawData
            .map { row ->
                DailyCases(
                    date = row.data,
                    newCases = row.confirmados_novos,
                )
            }
}



