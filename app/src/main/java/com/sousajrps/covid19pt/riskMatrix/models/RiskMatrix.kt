package com.sousajrps.covid19pt.riskMatrix.models

data class RiskMatrix(
    val date: String = "",
    val rt_national: Double = 0.0,
    val incidence_national: Double = 0.0,
    val rt_continent: Double = 0.0,
    val incidence_continent: Double = 0.0,
)

