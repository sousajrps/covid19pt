package com.sousajrps.covid19pt.lineChartView

import android.os.Bundle
import com.sousajrps.covid19pt.BaseActivity
import com.sousajrps.covid19pt.R

class LineChartActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_cases)

        val chartData: CustomChartData =
            intent.getParcelableExtra(CHART_DATA) ?: CustomChartData()

        val lineChartView = findViewById<LineChartView>(R.id.line_chart_view)
        lineChartView.setData(customChartData = chartData, isFullScreen = true)
    }

    companion object {
        const val CHART_DATA = "chartData"
    }
}
