package com.sousajrps.covid19pt.CustomChart

import com.sousajrps.covid19pt.remote.models.Data

object DataToCustomChartDataValues {
    fun map(
        rawData: List<Data>
    ): List<CustomChartDataValue> =
        rawData
            .map { row ->
                CustomChartDataValue(
                    date = row.data,
                    value = row.confirmados_novos,
                )
            }
}
