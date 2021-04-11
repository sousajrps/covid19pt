package com.sousajrps.covid19pt.riskMatrix.models

data class RiskMatrix(
    val date: String = "",
    val rt_national: Float = 0F,
    val incidence_national: Float = 0F,
    val rt_continent: Float = 0F,
    val incidence_continent: Float = 0F,
)

