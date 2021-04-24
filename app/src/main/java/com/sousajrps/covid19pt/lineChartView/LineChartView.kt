package com.sousajrps.covid19pt.lineChartView

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.sousajrps.covid19pt.R
import java.text.SimpleDateFormat
import java.util.*

class LineChartView : ConstraintLayout {
    private var titleTv: TextView
    private var titleFullScreenTv: TextView
    private var lineChart: LineChart
    private val calendar: Calendar
    private val backArrow: ImageView
    private val expandView: ImageView

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    init {
        val rootView = LayoutInflater.from(context).inflate(R.layout.line_chart_view, this, true)
        lineChart = rootView.findViewById(R.id.line_chart)
        titleTv = rootView.findViewById(R.id.title_tv)
        titleFullScreenTv = rootView.findViewById(R.id.title_full_screen_tv)
        backArrow = rootView.findViewById(R.id.back_arrow_iv)
        expandView = rootView.findViewById(R.id.expand_iv)
        calendar = Calendar.getInstance()
        iniView()
    }

    private fun iniView() {
        val textColor = ContextCompat.getColor(context, R.color.textColor)
        lineChart.description.isEnabled = false
        lineChart.setTouchEnabled(true)
        lineChart.isDragEnabled = true
        lineChart.setScaleEnabled(true)
        lineChart.isHighlightPerDragEnabled = true
        lineChart.setDrawMarkers(true)
        lineChart.isHighlightPerTapEnabled = true
        lineChart.setNoDataText("")
        lineChart.setViewPortOffsets(4f, 4f, 4f, 4f)
        lineChart.legend.isEnabled = true
        lineChart.legend.textColor = textColor
        lineChart.legend.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        lineChart.legend.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
        val mv = LineChartMarkerView(context, R.layout.marker_view)
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

    fun setData(
        customChartData: CustomChartData,
        isFullScreen: Boolean = false
    ) {
        if (isFullScreen) {
            backArrow.visibility = View.VISIBLE
            backArrow.setOnClickListener {
                (context as Activity).finish()
            }
            titleFullScreenTv.text = context.getString(customChartData.title)
            titleFullScreenTv.visibility = View.VISIBLE
            titleTv.visibility = View.GONE
            expandView.visibility = View.GONE

        } else {
            backArrow.visibility = View.GONE
            titleFullScreenTv.visibility = View.GONE
            titleTv.text = context.getString(customChartData.title)
            titleTv.visibility = View.VISIBLE
            expandView.visibility = View.VISIBLE
            expandView.setOnClickListener {
                val intent = Intent(context, LineChartActivity::class.java)
                intent.putExtra(LineChartActivity.CHART_DATA, customChartData)
                context.startActivity(intent)
            }
        }

        val data = LineData()
        customChartData.sets.forEach { list: CustomChartDataSet ->
            val chartLines = ContextCompat.getColor(context, list.colorLines)
            val chartCircles = ContextCompat.getColor(context, list.colorCircles)

            val continentLastDaysValues: List<Entry> =
                list.customChartDataValues.map { confirmedCases ->
                    Entry(
                        getTimeInMilliseconds(confirmedCases.date).toFloat(),
                        confirmedCases.value,
                        MarkerViewCustom(
                            label = confirmedCases.date,
                            labelColor = list.colorCircles
                        )
                    )
                }

            val set1 = LineDataSet(continentLastDaysValues, context.getString(list.label))
            set1.axisDependency = YAxis.AxisDependency.LEFT
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
        lineChart.animateXY(1000, 1000)
    }

    private fun getTimeInMilliseconds(dateString: String): Long {
        val sdf = SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH)
        sdf.parse(dateString)?.let {
            calendar.time = it
        }
        return calendar.time.time
    }
}
