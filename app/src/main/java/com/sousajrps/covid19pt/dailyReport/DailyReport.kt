package com.sousajrps.covid19pt.dailyReport

data class DailyReport(
    val date: String,
    val active: Int,
    val activeVariation: Int,
    val recovered: Int,
    val recoveredVariation: Int,
    val deaths: Int,
    val deathsVariation: Int,
    val surveillance: Int,
    val surveillanceVariation: Int,
    val confirmed: Int,
    val confirmedVariation: Int,
    val hospitalised: Int,
    val hospitalisedVariation: Int,
    val hospitalisedIcu: Int,
    val hospitalisedIcuVariation: Int
)

