package com.sousajrps.covid19pt.vaccination

import com.sousajrps.covid19pt.R
import com.sousajrps.covid19pt.remote.models.VaccinationWeekly

object DataToVaccinationWeeklyMapper {
    fun getItems(vaccinationWeekly: VaccinationWeekly) =
        VaccinationWeeklyUiModel(
            date = vaccinationWeekly.data,
            received = vaccinationWeekly.recebidas,
            distributed = vaccinationWeekly.distribuidas,
            vaccinationByAgeGroup = getVaccinationByAgeGroup(vaccinationWeekly)
        )

    private fun getVaccinationByAgeGroup(vaccinationWeekly: VaccinationWeekly): List<VaccinationByAgeGroup> {
        val typeList = listOf(
            AgeGroup.HEADER,
            AgeGroup.AGE_0_17,
            AgeGroup.AGE_18_24,
            AgeGroup.AGE_25_49,
            AgeGroup.AGE_50_64,
            AgeGroup.AGE_65_79,
            AgeGroup.AGE_80_PLUS
        )
        return typeList.map { ageGroup ->
            VaccinationByAgeGroup(
                ageGroup = ageGroup,
                label = getLabel(ageGroup),
                firstDose = getFirstDose(ageGroup, vaccinationWeekly),
                secondDose = getSecondDose(ageGroup, vaccinationWeekly),
                firstDosePercentage = getFirstDosePercentage(ageGroup, vaccinationWeekly),
                secondDosePercentage = getSecondDosePercentage(ageGroup, vaccinationWeekly)
            )
        }
    }

    private fun getLabel(ageGroup: AgeGroup) =
        when (ageGroup) {
            AgeGroup.HEADER -> 0
            AgeGroup.AGE_0_17 -> R.string.vaccination_age_group0_17
            AgeGroup.AGE_18_24 -> R.string.vaccination_age_group18_24
            AgeGroup.AGE_25_49 -> R.string.vaccination_age_group25_49
            AgeGroup.AGE_50_64 -> R.string.vaccination_age_group50_64
            AgeGroup.AGE_65_79 -> R.string.vaccination_age_group65_79
            AgeGroup.AGE_80_PLUS -> R.string.vaccination_age_group80_plus
        }

    private fun getFirstDose(ageGroup: AgeGroup, vaccinationWeekly: VaccinationWeekly): Float =
        when (ageGroup) {
            AgeGroup.HEADER -> 0F
            AgeGroup.AGE_0_17 -> vaccinationWeekly.doses1_0_17
            AgeGroup.AGE_18_24 -> vaccinationWeekly.doses1_18_24
            AgeGroup.AGE_25_49 -> vaccinationWeekly.doses1_25_49
            AgeGroup.AGE_50_64 -> vaccinationWeekly.doses1_50_64
            AgeGroup.AGE_65_79 -> vaccinationWeekly.doses1_65_79
            AgeGroup.AGE_80_PLUS -> vaccinationWeekly.doses1_80
        }

    private fun getSecondDose(ageGroup: AgeGroup, vaccinationWeekly: VaccinationWeekly): Float =
        when (ageGroup) {
            AgeGroup.HEADER -> 0f
            AgeGroup.AGE_0_17 -> vaccinationWeekly.doses2_0_17
            AgeGroup.AGE_18_24 -> vaccinationWeekly.doses2_18_24
            AgeGroup.AGE_25_49 -> vaccinationWeekly.doses2_25_49
            AgeGroup.AGE_50_64 -> vaccinationWeekly.doses2_50_64
            AgeGroup.AGE_65_79 -> vaccinationWeekly.doses2_65_79
            AgeGroup.AGE_80_PLUS -> vaccinationWeekly.doses2_80
        }

    private fun getFirstDosePercentage(
        ageGroup: AgeGroup,
        vaccinationWeekly: VaccinationWeekly
    ): Float =
        when (ageGroup) {
            AgeGroup.HEADER -> 0f
            AgeGroup.AGE_0_17 -> vaccinationWeekly.doses1_perc_0_17 * 100
            AgeGroup.AGE_18_24 -> vaccinationWeekly.doses1_perc_18_24 * 100
            AgeGroup.AGE_25_49 -> vaccinationWeekly.doses1_perc_25_49 * 100
            AgeGroup.AGE_50_64 -> vaccinationWeekly.doses1_perc_50_64 * 100
            AgeGroup.AGE_65_79 -> vaccinationWeekly.doses1_perc_65_79 * 100
            AgeGroup.AGE_80_PLUS -> vaccinationWeekly.doses1_perc_80 * 100
        }

    private fun getSecondDosePercentage(
        ageGroup: AgeGroup,
        vaccinationWeekly: VaccinationWeekly
    ): Float =
        when (ageGroup) {
            AgeGroup.HEADER -> 0f
            AgeGroup.AGE_0_17 -> vaccinationWeekly.doses2_perc_0_17 * 100
            AgeGroup.AGE_18_24 -> vaccinationWeekly.doses2_perc_18_24 * 100
            AgeGroup.AGE_25_49 -> vaccinationWeekly.doses2_perc_25_49 * 100
            AgeGroup.AGE_50_64 -> vaccinationWeekly.doses2_perc_50_64 * 100
            AgeGroup.AGE_65_79 -> vaccinationWeekly.doses2_perc_65_79 * 100
            AgeGroup.AGE_80_PLUS -> vaccinationWeekly.doses2_perc_80 * 100
        }
}
