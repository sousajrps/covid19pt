package com.sousajrps.covid19pt.vaccination

import com.sousajrps.covid19pt.lineChartView.CustomChartData

data class VaccinationUiModel(
    val vaccinationTotals: VaccinationTotals,
    val vaccinationReportItem: List<VaccinationReportItem>,
    val vaccinationWeeklyUiModel: VaccinationWeeklyUiModel,
    val vaccinationChartUiModel: CustomChartData,
    val vaccinationDailyChartUiModel: CustomChartData
)
