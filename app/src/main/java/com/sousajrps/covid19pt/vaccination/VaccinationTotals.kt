package com.sousajrps.covid19pt.vaccination

data class VaccinationTotals(
    val date: String,
    val firstDoseTotal: Int,
    val firstDosePercentage: Double,
    val secondDoseTotal: Int,
    val secondDosePercentage: Double,
    val withoutVaccination: Int,
    val withoutVaccinationPercentage: Double
)
