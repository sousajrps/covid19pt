package com.sousajrps.covid19pt.dailyReport

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sousajrps.covid19pt.lineChartView.LineChartActivity
import com.sousajrps.covid19pt.lineChartView.CustomChartData
import com.sousajrps.covid19pt.lineChartView.LineChartView
import com.sousajrps.covid19pt.lineChartView.LineChartViewActions
import com.sousajrps.covid19pt.R
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

        viewModel.dailyReport.observe(viewLifecycleOwner, Observer { dailyReport ->
            dateTv.text = getString(R.string.report_title_label, dailyReport.first().date)

            val dailyReportAdapter = DailyReportAdapter(requireContext(), dailyReport)
            dailyReportRv.adapter = dailyReportAdapter
            dailyReportRv.invalidate()
        })

        viewModel.showLoading.observe(viewLifecycleOwner, Observer { loading ->
            if (loading) {
                loadingView.visibility = View.VISIBLE
            } else {
                loadingView.visibility = View.GONE
            }
        })

        viewModel.dailyCases.observe(viewLifecycleOwner, Observer { dailyCases ->
            dailyCasesChart.setData(dailyCases, viewActions = object :
                LineChartViewActions {
                override fun expand() {
                    expandChart(dailyCases)
                }

                override fun finish() {
                    //n-op
                }
            }, false)
        })

        viewModel.hospitalized.observe(viewLifecycleOwner, Observer { dailyCases ->
            hospitalizedChart.setData(dailyCases, viewActions = object : LineChartViewActions {
                override fun expand() {
                    expandChart(dailyCases)
                }

                override fun finish() {
                    //n-op
                }
            }, false)
        })

        viewModel.totals.observe(viewLifecycleOwner, Observer { dailyCases ->
            totalCasesChart.setData(dailyCases, viewActions = object : LineChartViewActions {
                override fun expand() {
                    expandChart(dailyCases)
                }

                override fun finish() {
                    //n-op
                }
            }, false)
        })
    }

    private fun expandChart(dailyCases: CustomChartData) {
        val intent = Intent(requireContext(), LineChartActivity::class.java)
        intent.putExtra(LineChartActivity.CHART_DATA, dailyCases)
        requireActivity().startActivity(intent)
    }
}
