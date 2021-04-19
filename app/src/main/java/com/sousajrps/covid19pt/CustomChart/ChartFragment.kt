package com.sousajrps.covid19pt.CustomChart

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.components.YAxis.AxisDependency
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.sousajrps.covid19pt.R
import java.text.SimpleDateFormat
import java.util.*

class ChartFragment : Fragment() {
    private lateinit var lineChart: LineChart
    private lateinit var backArrow: View
    private lateinit var titleTv: TextView
    private lateinit var boxView: View
    private lateinit var expandView: View
    private lateinit var calendar: Calendar
    private var customChartData: CustomChartData = CustomChartData()
    private var isFullScreen: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            isFullScreen = it.getBoolean(ChartActivity.IS_FULL_SCREEN)
            customChartData = it.getParcelable(ChartActivity.CHART_DATA) ?: CustomChartData()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_chart, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lineChart = view.findViewById(R.id.daily_cases_line_chart)
        backArrow = view.findViewById(R.id.back_arrow)
        titleTv = view.findViewById(R.id.daily_cases_title_tv)
        boxView = view.findViewById(R.id.total_cases_chart_box)
        expandView = view.findViewById(R.id.expand_daily_cases_iv)
        expandView.setOnClickListener {
            val intent = Intent(requireContext(), ChartActivity::class.java)
            intent.putExtra(ChartActivity.CHART_DATA, customChartData)
            requireActivity().startActivity(intent)
        }
        calendar = Calendar.getInstance()
        iniView()
        setData()
    }

    private fun iniView() {
        if (isFullScreen) {
            backArrow.visibility = View.VISIBLE
            titleTv.visibility = View.VISIBLE
            boxView.visibility = View.GONE
            expandView.visibility = View.GONE
            titleTv.text = getString(customChartData.title)
            backArrow.setOnClickListener {
                requireActivity().finish()
            }
        } else {
            backArrow.visibility = View.GONE
            titleTv.visibility = View.GONE
            boxView.visibility = View.VISIBLE
            expandView.visibility = View.VISIBLE
        }

        val textColor = ContextCompat.getColor(requireContext(), R.color.textColor)
        lineChart.description.isEnabled = false
        lineChart.setTouchEnabled(true)
        lineChart.isDragEnabled = true
        lineChart.setScaleEnabled(true)
        lineChart.isHighlightPerDragEnabled = true
        lineChart.setDrawMarkers(true)
        lineChart.isHighlightPerTapEnabled = true
        lineChart.setNoDataText("")
        lineChart.setViewPortOffsets(4f, 4f, 4f, 4f)
        lineChart.legend.isEnabled = false
        val mv = DailyCasesMarkerView(requireContext(), R.layout.marker_view)
        lineChart.marker = mv

        val xAxis: XAxis = lineChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM_INSIDE
        xAxis.textSize = 10f
        xAxis.textColor = textColor
        xAxis.textColor = textColor
        xAxis.setAvoidFirstLastClipping(true)
        xAxis.valueFormatter = DayFormatter()

        val leftAxis: YAxis = lineChart.axisLeft
        leftAxis.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART)
        leftAxis.textSize = 10f
        leftAxis.textColor = textColor
        leftAxis.axisMinimum = 0f
        leftAxis.textColor = textColor

        val rightAxis: YAxis = lineChart.axisRight
        rightAxis.isEnabled = false
    }

    private fun setData() {
        val data = LineData()
        customChartData.sets.forEach { list: CustomChartDataSet ->
            val chartLines = ContextCompat.getColor(requireContext(), list.colorLines)
            val chartCircles = ContextCompat.getColor(requireContext(), list.colorCircles)

            val continentLastDaysValues: List<Entry> =
                list.customChartDataValues.map { confirmedCases ->
                    Entry(
                        getTimeInMilliseconds(confirmedCases.date).toFloat(),
                        confirmedCases.value,
                        confirmedCases.date
                    )
                }

            val set1 = LineDataSet(continentLastDaysValues, getString(list.label))
            set1.axisDependency = AxisDependency.LEFT
            set1.color = chartLines
            set1.lineWidth = 1f
            set1.setDrawCircles(true)
            set1.setDrawValues(false)
            set1.fillAlpha = 100
            set1.fillColor = chartLines
            set1.highLightColor = chartLines
            set1.setDrawCircleHole(true)
            set1.isHighlightEnabled = true
            set1.circleHoleColor = chartCircles
            set1.setCircleColor(chartCircles)
            data.addDataSet(set1)
        }

        lineChart.data = data
        lineChart.invalidate()
    }


    private fun getTimeInMilliseconds(dateString: String): Long {
        val sdf = SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH)
        calendar.time = sdf.parse(dateString)
        return calendar.time.time
    }


    companion object {

        fun newInstance(isFullScreen: Boolean = false, chartData: CustomChartData) =
            ChartFragment().apply {
                arguments = Bundle(2).apply {
                    putBoolean(ChartActivity.IS_FULL_SCREEN, isFullScreen)
                    putParcelable(
                        ChartActivity.CHART_DATA,
                        chartData
                    )
                }
            }
    }
}
