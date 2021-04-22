package com.sousajrps.covid19pt.vaccination

import com.sousajrps.covid19pt.lineChartView.CustomChartData
import com.sousajrps.covid19pt.lineChartView.CustomChartDataSet
import com.sousajrps.covid19pt.lineChartView.CustomChartDataValue
import com.sousajrps.covid19pt.R
import com.sousajrps.covid19pt.local.Vaccination
import kotlin.math.abs

object DataToVaccinationReportMapper {
    const val EMPTY = ""
    const val PLUS = "+"

    fun getItems(vaccination: Vaccination): List<VaccinationReportItem> {
        val typeList = listOf(
            VaccinationReportItemType.FIRST_DOSE,
            VaccinationReportItemType.SECOND_DOSE,
            VaccinationReportItemType.TOTAL,
        )
        return typeList.map { type ->
            VaccinationReportItem(
                date = vaccination.data,
                labelRes = getLabel(type),
                vaccinationValue = getValue(type, vaccination),
                vaccinationVariation = abs(getVariation(type, vaccination)),
                vaccinationVariationSignal = getVariationSignal(type, vaccination),
                vaccinationItemColor = getVariationColor(type, vaccination),
                vaccinationReportItemType = type
            )
        }
    }

    private fun getLabel(item: VaccinationReportItemType) =
        when (item) {
            VaccinationReportItemType.FIRST_DOSE -> R.string.vaccination_one_dose
            VaccinationReportItemType.SECOND_DOSE -> R.string.vaccination_second_dose
            VaccinationReportItemType.TOTAL -> R.string.vaccination_total_doses
        }

    private fun getVariation(item: VaccinationReportItemType, currentDay: Vaccination): Int {
        return when (item) {
            VaccinationReportItemType.FIRST_DOSE -> (currentDay.doses1_novas).toInt()
            VaccinationReportItemType.SECOND_DOSE -> (currentDay.doses2_novas).toInt()
            VaccinationReportItemType.TOTAL -> (currentDay.doses_novas).toInt()
        }
    }

    private fun getVariationSignal(
        item: VaccinationReportItemType,
        currentDay: Vaccination
    ): String {
        return when (item) {
            VaccinationReportItemType.FIRST_DOSE -> if ((currentDay.doses1_novas).toInt() > 0) PLUS else EMPTY
            VaccinationReportItemType.SECOND_DOSE -> if ((currentDay.doses2_novas).toInt() > 0) PLUS else EMPTY
            VaccinationReportItemType.TOTAL -> if ((currentDay.doses_novas).toInt() > 0) PLUS else EMPTY
        }
    }

    private fun getVariationColor(
        item: VaccinationReportItemType,
        currentDay: Vaccination
    ): VaccinationItemColor {
        return when (item) {
            VaccinationReportItemType.FIRST_DOSE -> if ((currentDay.doses1_novas).toInt() > 0) VaccinationItemColor.GREEN else VaccinationItemColor.RED
            VaccinationReportItemType.SECOND_DOSE -> if ((currentDay.doses2_novas).toInt() > 0) VaccinationItemColor.GREEN else VaccinationItemColor.RED
            VaccinationReportItemType.TOTAL -> if ((currentDay.doses_novas).toInt() > 0) VaccinationItemColor.GREEN else VaccinationItemColor.RED
        }
    }

    private fun getValue(item: VaccinationReportItemType, currentDay: Vaccination): Int {
        return when (item) {
            VaccinationReportItemType.FIRST_DOSE -> currentDay.doses1.toInt()
            VaccinationReportItemType.SECOND_DOSE -> currentDay.doses2.toInt()
            VaccinationReportItemType.TOTAL -> currentDay.doses.toInt()
        }
    }

    fun getVaccinationTotalsChartData(vaccination: List<Vaccination>): CustomChartData {
        return CustomChartData(
            title = R.string.vaccination_total_doses_chart_title,
            sets = listOf(
                CustomChartDataSet(
                    label = R.string.vaccination_total_doses,
                    colorLines = R.color.chartLines,
                    colorCircles = R.color.chartCircles,
                    dailyCases = vaccination.map {
                        CustomChartDataValue(
                            date = it.data,
                            value = it.doses
                        )
                    }
                ),
                CustomChartDataSet(
                    label = R.string.vaccination_one_dose,
                    colorLines = R.color.yellow,
                    colorCircles = R.color.yellow,
                    dailyCases = vaccination.map {
                        CustomChartDataValue(
                            date = it.data,
                            value = it.doses1
                        )
                    }
                ),
                CustomChartDataSet(
                    label = R.string.vaccination_one_dose,
                    colorLines = R.color.green,
                    colorCircles = R.color.green,
                    dailyCases = vaccination.map {
                        CustomChartDataValue(
                            date = it.data,
                            value = it.doses2
                        )
                    }
                )
            )
        )
    }
}
