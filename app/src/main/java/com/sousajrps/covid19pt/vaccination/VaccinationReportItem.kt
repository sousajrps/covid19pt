package com.sousajrps.covid19pt.vaccination

data class VaccinationReportItem(
    val date: String,
    val labelRes: Int,
    val vaccinationValue: Int,
    val vaccinationVariation: Int,
    val vaccinationItemColor: VaccinationItemColor,
    val vaccinationVariationSignal: String,
    val vaccinationReportItemType: VaccinationReportItemType,
)

enum class VaccinationReportItemType {
    FIRST_DOSE, SECOND_DOSE, TOTAL
}

enum class VaccinationItemColor {
    RED, GREEN
}
