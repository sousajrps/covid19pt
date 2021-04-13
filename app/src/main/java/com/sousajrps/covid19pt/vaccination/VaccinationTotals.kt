package com.sousajrps.covid19pt.vaccination

data class VaccinationTotals(
    val date: String,
    val firstDoseTotal: Int,
    val firstDosePercentage: Float,
    val secondDoseTotal: Int,
    val secondDosePercentage: Float,
    val withoutVaccination: Int,
    val withoutVaccinationPercentage: Float
)
