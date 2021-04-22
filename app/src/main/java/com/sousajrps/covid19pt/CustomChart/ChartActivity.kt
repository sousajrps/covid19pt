package com.sousajrps.covid19pt.CustomChart

import android.os.Bundle
import com.sousajrps.covid19pt.BaseActivity
import com.sousajrps.covid19pt.R

class ChartActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_cases)

        val chartData: CustomChartData =
            intent.getParcelableExtra(CHART_DATA) ?: CustomChartData()

        val lineChartView = findViewById<LineChartView>(R.id.line_chart_view)
        lineChartView.setData(
            customChartData = chartData,
            isFullScreen = true,
            viewActions = object : LineChartViewActions {
                override fun expand() {
                    // no-op
                }

                override fun finish() {
                    this@ChartActivity.finish()
                }
            })
    }

    companion object {
        const val CHART_DATA = "chartData"
    }
}
