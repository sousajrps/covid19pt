package com.sousajrps.covid19pt.vaccination

data class VaccinationWeeklyUiModel(
    val date: String,
    val received: Float,
    val distributed: Float,
    val vaccinationByAgeGroup: List<VaccinationByAgeGroup>
)

data class VaccinationByAgeGroup(
    val ageGroup: AgeGroup,
    val label: Int,
    val firstDose: Float,
    val secondDose: Float,
    val firstDosePercentage: Float,
    val secondDosePercentage: Float,
    )

enum class AgeGroup {
    HEADER,
    AGE_0_17,
    AGE_18_24,
    AGE_25_49,
    AGE_50_64,
    AGE_65_79,
    AGE_80_PLUS,
}
