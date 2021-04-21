package com.sousajrps.covid19pt.vaccination

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.sousajrps.covid19pt.CustomChart.ChartFragment
import com.sousajrps.covid19pt.CustomChart.CustomChartData
import com.sousajrps.covid19pt.CustomNumberFormatter
import com.sousajrps.covid19pt.R
import java.util.*
import kotlin.collections.ArrayList

class VaccinationFragment : Fragment() {
    private lateinit var viewModel: VaccinationViewModel
    private lateinit var vaccinationTitleTv: TextView
    private lateinit var vaccinationWeeklyTitleTv: TextView
    private lateinit var loadingView: View
    private lateinit var pieChart: PieChart
    private lateinit var dailyReportRv: RecyclerView
    private lateinit var weeklyReportRv: RecyclerView
    private lateinit var listGroupView: View
    private lateinit var chartViewGroup: View
    private lateinit var receivedTv: TextView
    private lateinit var distributedTv: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_vaccination, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vaccinationTitleTv = view.findViewById(R.id.vaccination_title_tv)
        vaccinationWeeklyTitleTv = view.findViewById(R.id.vaccination_weekly_title_tv)
        loadingView = view.findViewById(R.id.loading_view_vaccination)
        pieChart = view.findViewById(R.id.piechart)
        dailyReportRv = view.findViewById(R.id.report_rv)
        weeklyReportRv = view.findViewById(R.id.report_weekly_rv)
        listGroupView = view.findViewById(R.id.vaccination_list_container)
        chartViewGroup = view.findViewById(R.id.chart_group)
        receivedTv = view.findViewById(R.id.vaccination_weekly_received_tv)
        distributedTv = view.findViewById(R.id.vaccination_weekly_distributed_tv)
        dailyReportRv.layoutManager = LinearLayoutManager(requireContext())
        dailyReportRv.isNestedScrollingEnabled = false
        weeklyReportRv.layoutManager = LinearLayoutManager(requireContext())
        weeklyReportRv.isNestedScrollingEnabled = false
        initViewModelAndObserve()
    }

    private fun initViewModelAndObserve() {
        viewModel = ViewModelProvider(this, VaccinationViewModelFactory())
            .get(VaccinationViewModel::class.java)
        viewModel.getData(Date().time)

        viewModel.data.observe(viewLifecycleOwner, { data ->
            vaccinationTitleTv.text = getString(R.string.vaccination_title_label, data.vaccinationTotals.date)

            setChartData(data.vaccinationTotals)
            setRecyclerViewData(data.vaccinationReportItem)
            setRecyclerViewWeeklyData(data.vaccinationWeeklyUiModel)
            setChartFragment(data.vaccinationChartUiModel)
        })

        viewModel.showLoading.observe(viewLifecycleOwner, { loading ->
            if (loading) {
                loadingView.visibility = View.VISIBLE
            } else {
                loadingView.visibility = View.GONE
            }
        })
    }

    private fun setChartFragment(vaccinationChartUiModel: CustomChartData) {
        val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        val fragment: ChartFragment = ChartFragment.newInstance(
            chartData = vaccinationChartUiModel,
            isFullScreen = false
        )
        fragmentTransaction.add(R.id.vaccination_chart, fragment, "VaccinationChart")
        fragmentTransaction.commit()
    }

    private fun setChartData(data: VaccinationTotals) {
        val skyBlueTransparency =
            ContextCompat.getColor(requireContext(), R.color.skyBlueTransparency)
        val green = ContextCompat.getColor(requireContext(), R.color.green)
        val yellow = ContextCompat.getColor(requireContext(), R.color.honeyYellow)
        val textColor = ContextCompat.getColor(requireContext(), R.color.textColor)

        pieChart.setHoleColor(Color.TRANSPARENT)
        pieChart.description.isEnabled = false
        pieChart.setNoDataText("")
        pieChart.legend.isEnabled = true
        pieChart.legend.textColor = textColor
        pieChart.setUsePercentValues(true)
        pieChart.setEntryLabelColor(textColor)
        pieChart.setEntryLabelTextSize(14f)
        pieChart.setDrawEntryLabels(false)

        val pieEntryList: ArrayList<PieEntry> = ArrayList()
        pieEntryList.add(
            PieEntry(
                data.secondDosePercentage,
                getString(R.string.vaccination_chart_legend_two_doses)
            )
        )
        pieEntryList.add(
            PieEntry(
                data.firstDosePercentage,
                getString(R.string.vaccination_chart_legend_one_dose)
            )
        )
        pieEntryList.add(
            PieEntry(
                data.withoutVaccinationPercentage,
                getString(R.string.vaccination_chart_legend_unvaccinated)
            )
        )

        val dataSet = PieDataSet(pieEntryList, "")
        dataSet.valueFormatter = PercentFormatter(pieChart)
        dataSet.valueTextSize = 12f
        dataSet.valueTextColor = textColor
        dataSet.setColors(green, yellow, skyBlueTransparency)

        val data = PieData(dataSet)

        pieChart.data = data
        chartViewGroup.visibility = View.VISIBLE
        pieChart.animateXY(2000, 2000)
    }

    private fun setRecyclerViewData(data: List<VaccinationReportItem>) {
        val dailyReportAdapter = VaccinationReportAdapter(requireContext(), data)
        dailyReportRv.adapter = dailyReportAdapter
        dailyReportRv.invalidate()
        listGroupView.visibility = View.VISIBLE
    }

    private fun setRecyclerViewWeeklyData(data: VaccinationWeeklyUiModel) {
        val customNumberFormatter = CustomNumberFormatter
        vaccinationWeeklyTitleTv.text = getString(R.string.vaccination_title_weekly_label, data.date)
        receivedTv.text = customNumberFormatter.format(data.received)
        distributedTv.text = customNumberFormatter.format(data.distributed)

        val dailyReportAdapter = VaccinationReportWeeklyAdapter(requireContext(), data.vaccinationByAgeGroup)
        weeklyReportRv.adapter = dailyReportAdapter
        weeklyReportRv.invalidate()
    }
}
