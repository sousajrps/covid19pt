package com.sousajrps.covid19pt.dailyCases

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
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
import kotlin.collections.ArrayList

class DailyCasesFragment : Fragment() {
    private lateinit var viewModel: DailyCasesViewModel
    private lateinit var loadingView: View
    private lateinit var dailyCasesLineChart: LineChart
    private lateinit var backArrow: View
    private lateinit var titleTv: TextView
    private lateinit var calendar: Calendar
    private var chartData: List<DailyCases> = emptyList()
    private var isFullScreen: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.arguments?.let {
            isFullScreen = it.getBoolean(IS_FULL_SCREEN)
            chartData = it.getParcelableArrayList<DailyCases>(CHART_DATA) ?: emptyList()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_daily_cases, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadingView = view.findViewById(R.id.loading_view_daily_cases)
        dailyCasesLineChart = view.findViewById(R.id.daily_cases_line_chart)
        backArrow = view.findViewById(R.id.back_arrow)
        titleTv = view.findViewById(R.id.daily_cases_title_tv)
        calendar = Calendar.getInstance()
        initViewModelAndObserve()
        iniView()
        viewModel.getData(chartData = chartData, time = Date().time)
    }

    private fun iniView() {
        if (isFullScreen) {
            backArrow.visibility = View.VISIBLE
            titleTv.visibility = View.VISIBLE
            backArrow.setOnClickListener {
                requireActivity().finish()
            }
        } else {
            backArrow.visibility = View.GONE
            titleTv.visibility = View.GONE
            loadingView.visibility = View.GONE
        }

        val textColor = ContextCompat.getColor(requireContext(), R.color.textColor)
        dailyCasesLineChart.description.isEnabled = false
        dailyCasesLineChart.setTouchEnabled(true)
        dailyCasesLineChart.isDragEnabled = true
        dailyCasesLineChart.setScaleEnabled(true)
        dailyCasesLineChart.isHighlightPerDragEnabled = true
        dailyCasesLineChart.setDrawMarkers(true)
        dailyCasesLineChart.isHighlightPerTapEnabled = true
        dailyCasesLineChart.setNoDataText("")
        dailyCasesLineChart.setViewPortOffsets(4f, 4f, 4f, 4f)
        dailyCasesLineChart.legend.isEnabled = false
        val mv = DailyCasesMarkerView(requireContext(), R.layout.marker_view)
        dailyCasesLineChart.marker = mv

        val xAxis: XAxis = dailyCasesLineChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM_INSIDE
        xAxis.textSize = 10f
        xAxis.textColor = textColor
        xAxis.textColor = textColor
        xAxis.setAvoidFirstLastClipping(true)
        xAxis.valueFormatter = DayFormatter()

        val leftAxis: YAxis = dailyCasesLineChart.axisLeft
        leftAxis.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART)
        leftAxis.textSize = 10f
        leftAxis.textColor = textColor
        leftAxis.axisMinimum = 0f
        leftAxis.textColor = textColor

        val rightAxis: YAxis = dailyCasesLineChart.axisRight
        rightAxis.isEnabled = false
    }

    private fun initViewModelAndObserve() {
        viewModel = ViewModelProvider(this, DailyCasesViewModelFactory())
            .get(DailyCasesViewModel::class.java)

        viewModel.data.observe(viewLifecycleOwner, { data ->
            chartData = data
            setData()
        })

        viewModel.showLoading.observe(viewLifecycleOwner, { loading ->
            if (loading && isFullScreen) {
                loadingView.visibility = View.VISIBLE
            } else {
                loadingView.visibility = View.GONE
            }
        })
    }


    private fun setData() {
        val chartLines = ContextCompat.getColor(requireContext(), R.color.chartLines)
        val chartCircles = ContextCompat.getColor(requireContext(), R.color.chartCircles)

        val continentLastDaysValues: List<Entry> = chartData.map { confirmedCases ->
            Entry(
                getTimeInMilliseconds(confirmedCases.date).toFloat(),
                confirmedCases.newCases,
                confirmedCases.date
            )
        }

        val set1 = LineDataSet(continentLastDaysValues, "DataSet 1")
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

        val data = LineData(set1)

        dailyCasesLineChart.data = data
        dailyCasesLineChart.invalidate()
    }


    private fun getTimeInMilliseconds(dateString: String): Long {
        val sdf = SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH)
        calendar.time = sdf.parse(dateString)
        return calendar.time.time
    }


    companion object {
        private const val IS_FULL_SCREEN = "isFullScreen"
        private const val CHART_DATA = "chartData"

        fun newInstance(isFullScreen: Boolean = false, dailyCases: List<DailyCases> = emptyList()) =
            DailyCasesFragment().apply {
                arguments = Bundle(1).apply {
                    putBoolean(IS_FULL_SCREEN, isFullScreen)
                    putParcelableArrayList(CHART_DATA, ArrayList<DailyCases>(dailyCases))
                }
            }
    }
}
