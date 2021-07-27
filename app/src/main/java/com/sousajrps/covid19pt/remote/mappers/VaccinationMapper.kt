package com.sousajrps.covid19pt.remote.mappers

import com.sousajrps.covid19pt.local.Vaccination

object VaccinationMapper {
    private const val DATA = "data"
    private const val DOSES = "doses"
    private const val DOSES_NOVAS = "doses_novas"
    private const val DOSES1 = "doses1"
    private const val DOSES1_NOVAS = "doses1_novas"
    private const val DOSES2 = "doses2"
    private const val DOSES2_NOVAS = "doses2_novas"
    private const val PESSOAS_VACINADAS_COMPLETAMENTE = "pessoas_vacinadas_completamente"
    private const val PESSOAS_VACINADAS_COMPLETAMENTE_NOVAS = "pessoas_vacinadas_completamente_novas"
    private const val PESSOAS_VACINADAS_PARCIALMENTE = "pessoas_vacinadas_parcialmente"
    private const val PESSOAS_VACINADAS_PARCIALMENTE_NOVAS = "pessoas_vacinadas_parcialmente_novas"
    private const val PESSOAS_INOCULADAS = "pessoas_inoculadas"
    private const val PESSOAS_INOCULADAS_NOVAS = "pessoas_inoculadas_novas"
    private const val VACINAS = "vacinas"
    private const val VACINAS_NOVAS = "vacinas_novas"

    fun mapCovid19VaccinationRawData(rawData: List<Map<String, String>>): List<Vaccination> =
        rawData.map { row ->
            Vaccination(
                data = row[DATA].orEmpty(),
                doses = getDouble(row[DOSES]),
                doses_novas = getDouble(row[DOSES_NOVAS]),
                doses1 = getDouble(row[DOSES1]),
                doses1_novas = getDouble(row[DOSES1_NOVAS]),
                doses2 = getDouble(row[DOSES2]),
                doses2_novas = getDouble(row[DOSES2_NOVAS]),
                pessoas_vacinadas_completamente = getDouble(row[PESSOAS_VACINADAS_COMPLETAMENTE]),
                pessoas_vacinadas_completamente_novas = getDouble(row[PESSOAS_VACINADAS_COMPLETAMENTE_NOVAS]),
                pessoas_vacinadas_parcialmente = getDouble(row[PESSOAS_VACINADAS_PARCIALMENTE]),
                pessoas_vacinadas_parcialmente_novas = getDouble(row[PESSOAS_VACINADAS_PARCIALMENTE_NOVAS]),
                pessoas_inoculadas = getDouble(row[PESSOAS_INOCULADAS]),
                pessoas_inoculadas_novas = getDouble(row[PESSOAS_INOCULADAS_NOVAS]),
                vacinas = getDouble(row[VACINAS]),
                vacinas_novas = getDouble(row[VACINAS_NOVAS]),
            )
        }

    private fun getDouble(parameter: String?): Double {
        return parameter?.toDoubleOrNull() ?: 0.0
    }
}



