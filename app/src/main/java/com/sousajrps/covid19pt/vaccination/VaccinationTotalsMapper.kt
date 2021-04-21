package com.sousajrps.covid19pt.vaccination

import com.sousajrps.covid19pt.local.Vaccination

object VaccinationTotalsMapper {
    fun map(
        vaccinationData: Vaccination,
        portuguesePopulation:Int,
    ): VaccinationTotals {
        val firstDoseOnly = vaccinationData.doses1 - vaccinationData.doses2
        val secondDosePercentage = (vaccinationData.doses2 / portuguesePopulation) * 100
        val firstDoseOnlyPercentage = (firstDoseOnly / portuguesePopulation) * 100
        val withoutVaccination = portuguesePopulation - vaccinationData.doses1
        val withoutVaccinationPercentage = 100 - secondDosePercentage - firstDoseOnlyPercentage

        return VaccinationTotals(
            date = vaccinationData.data,
            secondDoseTotal = vaccinationData.doses2.toInt(),
            secondDosePercentage = secondDosePercentage,
            firstDoseTotal = vaccinationData.doses1.toInt(),
            firstDosePercentage = firstDoseOnlyPercentage,
            withoutVaccination = withoutVaccination.toInt(),
            withoutVaccinationPercentage = withoutVaccinationPercentage
        )
    }
}
