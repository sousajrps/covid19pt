package com.sousajrps.covid19pt.remote.mappers

import com.sousajrps.covid19pt.remote.models.Vaccination

object VaccinationMapper {
    private const val DATA = "data"
    private const val DOSES = "doses"
    private const val DOSES_NOVAS = "doses_novas"
    private const val DOSES1 = "doses1"
    private const val DOSES1_NOVAS = "doses1_novas"
    private const val DOSES2 = "doses2"
    private const val DOSES2_NOVAS = "doses2_novas"

    fun mapCovid19VaccinationRawData(rawData: List<Map<String, String>>): List<Vaccination> =
        rawData.map { row ->
            Vaccination(
                data = row[DATA].orEmpty(),
                doses = getFloat(row[DOSES]),
                doses_novas = getFloat(row[DOSES_NOVAS]),
                doses1 = getFloat(row[DOSES1]),
                doses1_novas = getFloat(row[DOSES1_NOVAS]),
                doses2 = getFloat(row[DOSES2]),
                doses2_novas = getFloat(row[DOSES2_NOVAS])
            )
        }

    private fun getFloat(parameter: String?): Float {
        return parameter?.toFloatOrNull() ?: 0F
    }
}



