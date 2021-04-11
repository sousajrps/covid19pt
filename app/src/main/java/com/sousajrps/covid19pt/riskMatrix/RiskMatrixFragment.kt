package com.sousajrps.covid19pt.riskMatrix

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.sousajrps.covid19pt.R
import com.sousajrps.covid19pt.riskMatrix.models.RiskMatrix
import java.util.*
import kotlin.collections.ArrayList

class RiskMatrixFragment : Fragment() {
    private lateinit var viewModel: RiskMatrixViewModel
    private lateinit var loadingView: View
    private lateinit var riskMatrixRecyclerView: RecyclerView
    private lateinit var riskMatrixChart: LineChart
    private lateinit var rtLabelTv: TextView
    private lateinit var rtMaxTv: TextView
    private lateinit var rtMiddleTv: TextView
    private lateinit var rtMinTv: TextView
    private lateinit var casesLabelTv: TextView
    private lateinit var casesMaxTv: TextView
    private lateinit var casesMiddleTv: TextView
    private lateinit var casesMinTv: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_risk_matrix, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadingView = view.findViewById(R.id.loading_view)

        riskMatrixRecyclerView = view.findViewById(R.id.risk_matrix_recycler_view)
        riskMatrixChart = view.findViewById(R.id.risk_matrix_Line_chart)
        rtLabelTv = view.findViewById(R.id.rt_label_tv)
        rtMaxTv = view.findViewById(R.id.rt_max_label)
        rtMiddleTv = view.findViewById(R.id.rt_middle_label)
        rtMinTv = view.findViewById(R.id.rt_min_label)
        casesLabelTv = view.findViewById(R.id.cases_label_tv)
        casesMaxTv = view.findViewById(R.id.cases_max_label)
        casesMiddleTv = view.findViewById(R.id.cases_middle_label)
        casesMinTv = view.findViewById(R.id.cases_min_label)
        iniViews()
        initViewModelAndObserve()

        viewModel.getData(time = Date().time)
    }

    private fun iniViews() {
        riskMatrixRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        rtLabelTv.text = getString(R.string.risk_matrix_fragment_transmission_rate)
        rtMaxTv.text = rt_max.toInt().toString()
        rtMiddleTv.text = rt_middle.toInt().toString()
        rtMinTv.text = rt_min.toInt().toString()

        casesLabelTv.text = getString(R.string.risk_matrix_fragment_cases_per_100k)
        casesMaxTv.text = cases_max.toInt().toString()
        casesMiddleTv.text = cases_middle.toInt().toString()
        casesMinTv.text = cases_min.toInt().toString()

        riskMatrixChart.legend.isEnabled = false
        riskMatrixChart.description.isEnabled = false
        riskMatrixChart.axisRight.isEnabled = false
        riskMatrixChart.axisRight.axisMaximum = 1f
        riskMatrixChart.axisRight.axisMinimum = 0f
        riskMatrixChart.isSelected = false
        riskMatrixChart.setViewPortOffsets(0f, 0f, 0f, 0f)
        riskMatrixChart.setNoDataText("")
        riskMatrixChart.setScaleEnabled(false)
    }

    private fun initViewModelAndObserve() {
        viewModel = ViewModelProvider(this, MatrixViewModelFactory())
            .get(RiskMatrixViewModel::class.java)

        viewModel.data.observe(viewLifecycleOwner, Observer { data ->
            drawChart(data)
        })

        viewModel.showLoading.observe(viewLifecycleOwner, Observer { loading ->
            if (loading) {
                loadingView.visibility = View.VISIBLE
            } else {
                loadingView.visibility = View.GONE
            }
        })
    }

    private fun drawChart(chartData: List<RiskMatrix>) {
        val black = ContextCompat.getColor(requireContext(), R.color.black)
        val white = ContextCompat.getColor(requireContext(), R.color.white)
        val skyBlue = ContextCompat.getColor(requireContext(), R.color.skyBlue)
        val charcoal = ContextCompat.getColor(requireContext(), R.color.charcoal)

        val yAxis = riskMatrixChart.axisLeft
        yAxis.axisMinimum = cases_min
        yAxis.axisMaximum = cases_max
        yAxis.granularity = cases_middle
        yAxis.setDrawLabels(false)

        val xl = riskMatrixChart.xAxis
        xl.axisMinimum = rt_min
        xl.axisMaximum = rt_max
        xl.granularity = rt_middle
        xl.position = XAxis.XAxisPosition.BOTTOM
        xl.setDrawLabels(false)

        val nationalTodayValue: ArrayList<Entry> = ArrayList()
        if (chartData.isNotEmpty()) {
            nationalTodayValue.add(
                Entry(
                    chartData.first().rt_national,
                    chartData.first().incidence_national
                )
            )
        }

        val nationalToday = LineDataSet(nationalTodayValue, "nationalToday")
        nationalToday.setDrawValues(false)
        nationalToday.color = black
        nationalToday.setCircleColor(black)
        nationalToday.circleHoleColor = white
        nationalToday.circleHoleRadius = 3f
        nationalToday.circleRadius = 5f
        nationalToday.isHighlightEnabled = false

        val continentTodayValue: ArrayList<Entry> = ArrayList()
        if (chartData.isNotEmpty()) {
            continentTodayValue.add(
                Entry(
                    chartData.first().rt_continent,
                    chartData.first().incidence_continent
                )
            )
        }

        val continentToday = LineDataSet(continentTodayValue, "continentToday")
        continentToday.setDrawValues(false)
        continentToday.color = black
        continentToday.setCircleColor(charcoal)
        continentToday.circleHoleColor = skyBlue
        continentToday.circleHoleRadius = 3f
        continentToday.circleRadius = 5f
        continentToday.isHighlightEnabled = false


        val nationalLastDaysValues: List<Entry> =
            chartData.map { Entry(it.rt_national, it.incidence_national) }
                .sortedBy { entry -> entry.x }

        val nationalLastDays = LineDataSet(nationalLastDaysValues, "nationalLastDays")
        nationalLastDays.setDrawValues(false)
        nationalLastDays.color = black
        nationalLastDays.setCircleColor(black)
        nationalLastDays.circleHoleColor = white
        nationalLastDays.circleHoleRadius = 2f
        nationalLastDays.circleRadius = 3f
        nationalLastDays.isHighlightEnabled = false


        val continentLastDaysValues: List<Entry> =
            chartData.map { Entry(it.rt_continent, it.incidence_continent) }
                .sortedBy { entry -> entry.x }

        val continentLastDays = LineDataSet(continentLastDaysValues, "continentLastDays")
        continentLastDays.setDrawValues(false)
        continentLastDays.color = black
        continentLastDays.setCircleColor(charcoal)
        continentLastDays.circleHoleColor = skyBlue
        continentLastDays.circleHoleRadius = 2f
        continentLastDays.circleRadius = 3f
        continentLastDays.isHighlightEnabled = false

        val lineData = LineData()
        lineData.addDataSet(nationalLastDays)
        lineData.addDataSet(continentLastDays)
        lineData.addDataSet(continentToday)
        lineData.addDataSet(nationalToday)

        riskMatrixChart.data = lineData
        riskMatrixChart.invalidate()

        val riskMatrixAdapter = RiskMatrixAdapter(requireContext(), chartData)
        riskMatrixRecyclerView.adapter = riskMatrixAdapter
        riskMatrixRecyclerView.invalidate()
    }
}
