package com.sousajrps.covid19pt.dailyReport

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sousajrps.covid19pt.R
import com.sousajrps.covid19pt.lineChartView.LineChartView
import java.util.*

class DailyReportFragment : Fragment() {
    private lateinit var viewModel: ReportViewModel
    private lateinit var loadingView: View
    private lateinit var dailyReportRv: RecyclerView
    private lateinit var dateTv: TextView
    private lateinit var dailyCasesChart: LineChartView
    private lateinit var hospitalizedChart: LineChartView
    private lateinit var totalCasesChart: LineChartView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_daily_report, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadingView = view.findViewById(R.id.loading_view_report)
        dailyReportRv = view.findViewById(R.id.report_rv)
        dateTv = view.findViewById(R.id.date_label_tv)
        dailyCasesChart = view.findViewById(R.id.daily_cases_line_chart)
        hospitalizedChart = view.findViewById(R.id.hospitalized_chart)
        totalCasesChart = view.findViewById(R.id.total_cases_chart)
        dailyReportRv.layoutManager = LinearLayoutManager(requireContext())
        dailyReportRv.isNestedScrollingEnabled = false

        initAndObserveViewModel()
        viewModel.getData(Date().time)
    }

    private fun initAndObserveViewModel() {
        viewModel = ViewModelProvider(this, ReportViewModelFactory())
            .get(ReportViewModel::class.java)

        viewModel.dailyReport.observe(viewLifecycleOwner, { dailyReport ->
            dateTv.text = getString(R.string.report_title_label, dailyReport.report.first().date)
            showDailyReport(dailyReport.report)
            dailyCasesChart.setData(dailyReport.dailyCases)
            hospitalizedChart.setData(dailyReport.hospitalized)
            totalCasesChart.setData(dailyReport.totalCases)
        })

        viewModel.showLoading.observe(viewLifecycleOwner, { loading ->
            if (loading) {
                loadingView.visibility = View.VISIBLE
            } else {
                loadingView.visibility = View.GONE
            }
        })
    }

    private fun showDailyReport(dailyReport: List<DailyReportItem>) {
        val dailyReportAdapter = DailyReportAdapter(requireContext(), dailyReport)
        dailyReportRv.adapter = dailyReportAdapter
        dailyReportRv.invalidate()
    }
}
