package com.sousajrps.covid19pt.dailyReport

import com.sousajrps.covid19pt.lineChartView.CustomChartData

data class DailyReportUiModel(
    val report: List<DailyReportItem>,
    val dailyCases: CustomChartData,
    val hospitalized: CustomChartData,
    val totalCases: CustomChartData
)



