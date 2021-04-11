package com.sousajrps.covid19pt.dailyReport

data class DailyReportItem(
    val date: String,
    val labelRes: Int,
    val totalValue: Int,
    val variationValue: Int,
    val variationColor: DailyReportItemColor,
    val variationSignal: String,
    val dailyReportItemType: DailyReportItemType,
)



