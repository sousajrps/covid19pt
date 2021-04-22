package com.sousajrps.covid19pt.dailyReport

import com.sousajrps.covid19pt.lineChartView.CustomChartDataValue
import com.sousajrps.covid19pt.local.Data

object DataToCustomChartDataValues {
    fun mapDailyCases(
        rawData: List<Data>
    ): List<CustomChartDataValue> =
        rawData
            .map { row ->
                CustomChartDataValue(
                    date = row.data,
                    value = row.confirmados_novos,
                )
            }


    fun mapHospitalized(
        rawData: List<Data>
    ): List<CustomChartDataValue> =
        rawData
            .map { row ->
                CustomChartDataValue(
                    date = row.data,
                    value = row.internados,
                )
            }

    fun mapHospitalizedIcu(
        rawData: List<Data>
    ): List<CustomChartDataValue> =
        rawData
            .map { row ->
                CustomChartDataValue(
                    date = row.data,
                    value = row.internados_uci,
                )
            }

    fun mapRecovered(
        rawData: List<Data>
    ): List<CustomChartDataValue> =
        rawData
            .map { row ->
                CustomChartDataValue(
                    date = row.data,
                    value = row.recuperados,
                )
            }

    fun mapActive(
        rawData: List<Data>
    ): List<CustomChartDataValue> =
        rawData
            .map { row ->
                CustomChartDataValue(
                    date = row.data,
                    value = row.ativos,
                )
            }

    fun mapDeath(
        rawData: List<Data>
    ): List<CustomChartDataValue> =
        rawData
            .map { row ->
                CustomChartDataValue(
                    date = row.data,
                    value = row.obitos,
                )
            }
}
