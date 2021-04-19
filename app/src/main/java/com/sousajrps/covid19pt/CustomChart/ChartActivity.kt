package com.sousajrps.covid19pt.CustomChart

import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.sousajrps.covid19pt.BaseActivity
import com.sousajrps.covid19pt.R

class ChartActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_cases)

        val chartData: CustomChartData =
            intent.getParcelableExtra(CHART_DATA) ?: CustomChartData()

        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        val fragment: ChartFragment =
            ChartFragment.newInstance(isFullScreen = true, chartData = chartData)
        fragmentTransaction.add(R.id.fragment_container, fragment, "DailyCasesFragment")
        fragmentTransaction.commit()
    }

    companion object {
        const val IS_FULL_SCREEN = "isFullScreen"
        const val CHART_DATA = "chartData"
    }
}
