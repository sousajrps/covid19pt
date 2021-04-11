package com.sousajrps.covid19pt.dailyReport

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sousajrps.covid19pt.R
import com.sousajrps.covid19pt.dailyCases.DailyCases
import com.sousajrps.covid19pt.dailyCases.DailyCasesActivity
import com.sousajrps.covid19pt.dailyCases.DailyCasesFragment
import java.util.*

class DailyReportFragment : Fragment() {
    private lateinit var viewModel: ReportViewModel
    private lateinit var loadingView: View
    private lateinit var expandDailyCasesChartIv: View
    private lateinit var dailyReportRv: RecyclerView
    private lateinit var dateTv: TextView
    private lateinit var dateDivider: View
    private lateinit var totalCasesBox: View
    private lateinit var totalCasesTitle: View

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
        dateDivider = view.findViewById(R.id.date_divider)
        totalCasesBox = view.findViewById(R.id.total_cases_chart_box)
        totalCasesTitle = view.findViewById(R.id.total_cases_chart_title_tv)

        dailyReportRv.layoutManager = LinearLayoutManager(requireContext())
        dailyReportRv.isNestedScrollingEnabled = false

        expandDailyCasesChartIv = view.findViewById(R.id.expand_daily_cases_iv)
        expandDailyCasesChartIv.setOnClickListener {
            val intent = Intent(requireContext(), DailyCasesActivity::class.java)
            requireActivity().startActivity(intent)
        }

        initAndObserveViewModel()
        viewModel.getData(Date().time)
    }

    private fun initAndObserveViewModel() {
        viewModel = ViewModelProvider(this, ReportViewModelFactory())
            .get(ReportViewModel::class.java)

        viewModel.data.observe(viewLifecycleOwner, Observer { data ->
            dateTv.text = getString(R.string.report_title_label, data.first().date)
            dateDivider.visibility = View.VISIBLE

            val dailyReportAdapter = DailyReportAdapter(requireContext(), data)
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
            setupDailyCasesFragment(dailyCases)
        })
    }

    private fun setupDailyCasesFragment(dailyCases: List<DailyCases>) {
        expandDailyCasesChartIv.visibility = View.VISIBLE
        totalCasesBox.visibility = View.VISIBLE
        totalCasesTitle.visibility = View.VISIBLE
        val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        val fragment: DailyCasesFragment = DailyCasesFragment.newInstance(
            dailyCases = dailyCases,
            isFullScreen = false
        )
        fragmentTransaction.add(R.id.fragment_container, fragment, "DailyCasesFragment")
        fragmentTransaction.commit()
    }
}
