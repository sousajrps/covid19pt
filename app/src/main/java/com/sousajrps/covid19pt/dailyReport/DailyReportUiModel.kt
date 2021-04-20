package com.sousajrps.covid19pt.dailyReport

import com.sousajrps.covid19pt.CustomChart.CustomChartDataValue

data class DailyReportUiModel(
    val report: List<DailyReportItem>,
    val dailyCases: List<CustomChartDataValue>,
    val hospitalized: List<CustomChartDataValue>,
    val hospitalizedIcu: List<CustomChartDataValue>,
    val active: List<CustomChartDataValue>,
    val recovered: List<CustomChartDataValue>,
    val deaths: List<CustomChartDataValue>,
)



