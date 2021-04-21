package com.sousajrps.covid19pt.vaccination

import com.sousajrps.covid19pt.CustomChart.CustomChartData

data class VaccinationUiModel(
    val vaccinationTotals: VaccinationTotals,
    val vaccinationReportItem: List<VaccinationReportItem>,
    val vaccinationWeeklyUiModel: VaccinationWeeklyUiModel,
    val vaccinationChartUiModel: CustomChartData
)
