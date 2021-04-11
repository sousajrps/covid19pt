package com.sousajrps.covid19pt.dailyReport

import com.sousajrps.covid19pt.R
import com.sousajrps.covid19pt.remote.models.Data
import kotlin.math.abs

object DataToDailyReportMapper {
    const val MINUS = "-"
    const val PLUS = "+"

    fun getReportItems(rawData: List<Data>): List<DailyReportItem> {
        val currentDay = rawData[rawData.size - 1]
        val previousDay = rawData[rawData.size - 2]
        val typeList = listOf(
            DailyReportItemType.CONFIRMED,
            DailyReportItemType.ACTIVE,
            DailyReportItemType.RECOVERED,
            DailyReportItemType.DEATHS,
            DailyReportItemType.SURVEILLANCE,
            DailyReportItemType.HOSPITALIZED,
            DailyReportItemType.HOSPITALIZED_ICU
        )
        return typeList.map { type ->
            DailyReportItem(
                date = currentDay.data,
                labelRes = getLabel(type),
                totalValue = getValue(type, currentDay),
                variationValue = abs(getVariation(type, currentDay, previousDay)),
                variationSignal = getVariationSignal(type, currentDay, previousDay),
                variationColor = getVariationColor(type, currentDay, previousDay),
                dailyReportItemType = type
            )
        }
    }

    private fun getLabel(item: DailyReportItemType) =
        when (item) {
            DailyReportItemType.CONFIRMED -> R.string.report_confirmed
            DailyReportItemType.ACTIVE -> R.string.report_active
            DailyReportItemType.RECOVERED -> R.string.report_recovered
            DailyReportItemType.DEATHS -> R.string.report_deaths
            DailyReportItemType.SURVEILLANCE -> R.string.report_surveillance
            DailyReportItemType.HOSPITALIZED -> R.string.report_hospitalized
            DailyReportItemType.HOSPITALIZED_ICU -> R.string.report_hospitalized_icu
        }

    private fun getVariation(item: DailyReportItemType, currentDay: Data, previousDay: Data): Int {
        return when (item) {
            DailyReportItemType.CONFIRMED -> (currentDay.confirmados_novos).toInt()
            DailyReportItemType.ACTIVE -> (currentDay.ativos - previousDay.ativos).toInt()
            DailyReportItemType.RECOVERED -> (currentDay.recuperados - previousDay.recuperados).toInt()
            DailyReportItemType.DEATHS -> (currentDay.obitos - previousDay.obitos).toInt()
            DailyReportItemType.SURVEILLANCE -> (currentDay.vigilancia - previousDay.vigilancia).toInt()
            DailyReportItemType.HOSPITALIZED -> (currentDay.internados - previousDay.internados).toInt()
            DailyReportItemType.HOSPITALIZED_ICU -> (currentDay.internados_uci - previousDay.internados_uci).toInt()
        }
    }

    private fun getVariationSignal(
        item: DailyReportItemType,
        currentDay: Data,
        previousDay: Data
    ): String {
        return when (item) {
            DailyReportItemType.CONFIRMED -> if ((currentDay.confirmados_novos).toInt() >= 0) PLUS else MINUS
            DailyReportItemType.ACTIVE -> if ((currentDay.ativos - previousDay.ativos).toInt() >= 0) PLUS else MINUS
            DailyReportItemType.RECOVERED -> if ((currentDay.recuperados - previousDay.recuperados).toInt() >= 0) PLUS else MINUS
            DailyReportItemType.DEATHS -> if ((currentDay.obitos - previousDay.obitos).toInt() >= 0) PLUS else MINUS
            DailyReportItemType.SURVEILLANCE -> if ((currentDay.vigilancia - previousDay.vigilancia).toInt() >= 0) PLUS else MINUS
            DailyReportItemType.HOSPITALIZED -> if ((currentDay.internados - previousDay.internados).toInt() >= 0) PLUS else MINUS
            DailyReportItemType.HOSPITALIZED_ICU -> if ((currentDay.internados_uci - previousDay.internados_uci).toInt() >= 0) PLUS else MINUS
        }
    }

    private fun getVariationColor(
        item: DailyReportItemType,
        currentDay: Data,
        previousDay: Data
    ): DailyReportItemColor {
        return when (item) {
            DailyReportItemType.CONFIRMED -> if ((currentDay.confirmados_novos).toInt() > 0) DailyReportItemColor.RED else DailyReportItemColor.GREEN
            DailyReportItemType.ACTIVE -> if ((currentDay.ativos - previousDay.ativos).toInt() > 0) DailyReportItemColor.RED else DailyReportItemColor.GREEN
            DailyReportItemType.RECOVERED -> if ((currentDay.recuperados - previousDay.recuperados).toInt() > 0) DailyReportItemColor.GREEN else DailyReportItemColor.RED
            DailyReportItemType.DEATHS -> if ((currentDay.obitos - previousDay.obitos).toInt() > 0) DailyReportItemColor.RED else DailyReportItemColor.GREEN
            DailyReportItemType.SURVEILLANCE -> if ((currentDay.vigilancia - previousDay.vigilancia).toInt() > 0) DailyReportItemColor.RED else DailyReportItemColor.GREEN
            DailyReportItemType.HOSPITALIZED -> if ((currentDay.internados - previousDay.internados).toInt() > 0) DailyReportItemColor.RED else DailyReportItemColor.GREEN
            DailyReportItemType.HOSPITALIZED_ICU -> if ((currentDay.internados_uci - previousDay.internados_uci).toInt() > 0) DailyReportItemColor.RED else DailyReportItemColor.GREEN
        }
    }

    private fun getValue(item: DailyReportItemType, currentDay: Data): Int {
        return when (item) {
            DailyReportItemType.CONFIRMED -> currentDay.confirmados.toInt()
            DailyReportItemType.ACTIVE -> currentDay.ativos.toInt()
            DailyReportItemType.RECOVERED -> currentDay.recuperados.toInt()
            DailyReportItemType.DEATHS -> currentDay.obitos.toInt()
            DailyReportItemType.SURVEILLANCE -> currentDay.vigilancia.toInt()
            DailyReportItemType.HOSPITALIZED -> currentDay.internados.toInt()
            DailyReportItemType.HOSPITALIZED_ICU -> currentDay.internados_uci.toInt()
        }
    }
}

enum class DailyReportItemType {
    CONFIRMED, ACTIVE, RECOVERED, DEATHS, SURVEILLANCE, HOSPITALIZED, HOSPITALIZED_ICU
}

enum class DailyReportItemColor {
    RED, GREEN
}

enum class DailyReportItemSignal {
    PLUS, MINUS
}




