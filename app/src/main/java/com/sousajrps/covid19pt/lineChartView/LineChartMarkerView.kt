package com.sousajrps.covid19pt.lineChartView

import android.content.Context
import android.util.Log
import android.widget.TextView
import com.github.mikephil.charting.components.IMarker
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.MPPointF
import com.sousajrps.covid19pt.CustomNumberFormatter
import com.sousajrps.covid19pt.R

class LineChartMarkerView : MarkerView, IMarker {

    private var markerTv: TextView = findViewById(R.id.marker_tv)

    constructor(context: Context?) : super(context, 0)
    constructor(context: Context?, layoutResource: Int) : super(context, layoutResource)

    override fun refreshContent(e: Entry, highlight: Highlight) {
        try {
            val markerViewCustom: MarkerViewCustom = e.data as MarkerViewCustom
            markerTv.text = context.getString(
                R.string.marker_label,
                markerViewCustom.label,
                CustomNumberFormatter.format(e.y)
            )
            markerTv.setBackgroundColor(context.resources.getColor(markerViewCustom.labelColor))
        } catch (e: Exception) {
            Log.e("LineChartMarkerView", "" + e.localizedMessage)
        }
    }

    override fun getOffset(): MPPointF =
        MPPointF((-width - 10).toFloat(), (-height - 10).toFloat())
}

data class MarkerViewCustom(
    val label: String,
    val labelColor: Int
)
