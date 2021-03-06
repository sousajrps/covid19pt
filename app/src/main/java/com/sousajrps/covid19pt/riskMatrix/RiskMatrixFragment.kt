package com.sousajrps.covid19pt.riskMatrix

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.sousajrps.covid19pt.R
import com.sousajrps.covid19pt.remote.models.MatrixParameters
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
    private lateinit var matrixParameters: MatrixParameters

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
        initViewModelAndObserve()

        viewModel.getData(time = Date().time)
    }

    private fun initViews(matrixParameters: MatrixParameters) {
        this.matrixParameters = matrixParameters

        riskMatrixRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        rtLabelTv.text = getString(R.string.risk_matrix_fragment_transmission_rate)
        rtMaxTv.text = matrixParameters.rtMax.toString()
        rtMiddleTv.text = matrixParameters.rtMiddle.toString()
        rtMinTv.text = matrixParameters.rtMin.toString()

        casesLabelTv.text = getString(R.string.risk_matrix_fragment_cases_per_100k)
        casesMaxTv.text = matrixParameters.casesMax.toInt().toString()
        casesMiddleTv.text = matrixParameters.casesMiddle.toInt().toString()
        casesMinTv.text = matrixParameters.casesMin.toInt().toString()

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

        viewModel.initViews()

        viewModel.matrixParameters.observe(viewLifecycleOwner, { matrixParameters ->
            initViews(matrixParameters)
        })

        viewModel.data.observe(viewLifecycleOwner, { data ->
            drawChart(data)
        })

        viewModel.showLoading.observe(viewLifecycleOwner, { loading ->
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
        yAxis.axisMinimum = matrixParameters.casesMin
        yAxis.axisMaximum = matrixParameters.casesMax
        yAxis.granularity = matrixParameters.casesMiddle
        yAxis.setDrawLabels(false)

        val xl = riskMatrixChart.xAxis
        xl.axisMinimum = matrixParameters.rtMin
        xl.axisMaximum = matrixParameters.rtMax
        xl.granularity = matrixParameters.rtMiddle
        xl.position = XAxis.XAxisPosition.BOTTOM
        xl.setDrawLabels(false)

        val nationalTodayValue: ArrayList<Entry> = ArrayList()
        if (chartData.isNotEmpty()) {
            nationalTodayValue.add(
                Entry(
                    chartData.first().rt_national.toFloat(),
                    chartData.first().incidence_national.toFloat()
                )
            )
        }

        val nationalToday = LineDataSet(nationalTodayValue, "nationalToday")
        nationalToday.setDrawValues(false)
        nationalToday.color = Color.TRANSPARENT
        nationalToday.setCircleColor(black)
        nationalToday.circleHoleColor = white
        nationalToday.circleHoleRadius = 4f
        nationalToday.circleRadius = 6f
        nationalToday.isHighlightEnabled = false

        val continentTodayValue: ArrayList<Entry> = ArrayList()
        if (chartData.isNotEmpty()) {
            continentTodayValue.add(
                Entry(
                    chartData.first().rt_continent.toFloat(),
                    chartData.first().incidence_continent.toFloat()
                )
            )
        }

        val continentToday = LineDataSet(continentTodayValue, "continentToday")
        continentToday.setDrawValues(false)
        continentToday.color = Color.TRANSPARENT
        continentToday.setCircleColor(charcoal)
        continentToday.circleHoleColor = skyBlue
        continentToday.circleHoleRadius = 4f
        continentToday.circleRadius = 6f
        continentToday.isHighlightEnabled = false


        val nationalLastDaysValues: List<Entry> =
            chartData.map { Entry(it.rt_national.toFloat(), it.incidence_national.toFloat()) }
                .sortedBy { entry -> entry.x }

        val nationalLastDays = LineDataSet(nationalLastDaysValues, "nationalLastDays")
        nationalLastDays.setDrawValues(false)
        nationalLastDays.color = Color.TRANSPARENT
        nationalLastDays.setCircleColor(black)
        nationalLastDays.circleHoleColor = white
        nationalLastDays.circleHoleRadius = 2f
        nationalLastDays.circleRadius = 3f
        nationalLastDays.isHighlightEnabled = false


        val continentLastDaysValues: List<Entry> =
            chartData.map { Entry(it.rt_continent.toFloat(), it.incidence_continent.toFloat()) }
                .sortedBy { entry -> entry.x }

        val continentLastDays = LineDataSet(continentLastDaysValues, "continentLastDays")
        continentLastDays.setDrawValues(false)
        continentLastDays.color = Color.TRANSPARENT
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

        val riskMatrixAdapter = RiskMatrixAdapter(requireContext(), matrixParameters, chartData)
        riskMatrixRecyclerView.adapter = riskMatrixAdapter
        riskMatrixRecyclerView.invalidate()
    }
}
