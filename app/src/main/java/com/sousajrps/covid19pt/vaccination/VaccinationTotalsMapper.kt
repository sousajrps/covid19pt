package com.sousajrps.covid19pt.vaccination

import com.sousajrps.covid19pt.remote.models.Vaccination
import com.sousajrps.covid19pt.riskMatrix.PORTUGUESE_POPULATION

object VaccinationTotalsMapper {
    fun map(
        vaccinationData: Vaccination,
    ): VaccinationTotals {
        val firstDoseOnly = vaccinationData.doses1 - vaccinationData.doses2
        val secondDosePercentage = (vaccinationData.doses2 / PORTUGUESE_POPULATION) * 100
        val firstDoseOnlyPercentage = (firstDoseOnly / PORTUGUESE_POPULATION) * 100
        val withoutVaccination = PORTUGUESE_POPULATION - vaccinationData.doses1
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
