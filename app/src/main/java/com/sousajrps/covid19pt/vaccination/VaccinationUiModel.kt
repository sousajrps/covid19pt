package com.sousajrps.covid19pt.vaccination

data class VaccinationUiModel(
    val vaccinationTotals: VaccinationTotals,
    val vaccinationReportItem: List<VaccinationReportItem>,
    val vaccinationWeeklyUiModel: VaccinationWeeklyUiModel
)
