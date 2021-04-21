package com.sousajrps.covid19pt.riskMatrix

import com.sousajrps.covid19pt.riskMatrix.models.RiskMatrix
import com.sousajrps.covid19pt.local.Data

object DataToMatrixMapper {
    private const val DEFAULT_NUMBER_OF_ITEMS = 15

    fun map(
        rawData: List<Data>,
        numberOfItems: Int = DEFAULT_NUMBER_OF_ITEMS
    ): List<RiskMatrix> =
        rawData
            .reversed()
            .filter { row ->
                row.data.isNotEmpty() && row.rt_nacional != 0F && row.incidencia_nacional != 0F
                        && row.rt_continente != 0F && row.incidencia_continente != 0F

            }
            .take(numberOfItems)
            .map { row ->
                RiskMatrix(
                    date = row.data,
                    rt_national = row.rt_nacional,
                    incidence_national = row.incidencia_nacional,
                    rt_continent = row.rt_continente,
                    incidence_continent = row.incidencia_continente
                )
            }
}



