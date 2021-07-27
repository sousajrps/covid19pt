package com.sousajrps.covid19pt.vaccination

import com.sousajrps.covid19pt.local.Vaccination

object VaccinationTotalsMapper {
    fun map(
        vaccinationData: Vaccination,
        portuguesePopulation:Int,
    ): VaccinationTotals {
        val firstDoseOnly = vaccinationData.pessoas_vacinadas_parcialmente
        val secondDosePercentage = (vaccinationData.pessoas_vacinadas_completamente / portuguesePopulation) * 100
        val firstDoseOnlyPercentage = (firstDoseOnly / portuguesePopulation) * 100
        val withoutVaccination = portuguesePopulation - vaccinationData.pessoas_vacinadas_parcialmente - vaccinationData.pessoas_vacinadas_completamente
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
