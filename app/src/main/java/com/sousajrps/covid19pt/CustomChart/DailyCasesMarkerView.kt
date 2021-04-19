package com.sousajrps.covid19pt.CustomChart

import android.content.Context
import android.widget.TextView
import com.github.mikephil.charting.components.IMarker
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.MPPointF
import com.sousajrps.covid19pt.R

class DailyCasesMarkerView : MarkerView, IMarker {

    private var markerTv: TextView = findViewById(R.id.marker_tv)

    constructor(context: Context?, layoutResource: Int) : super(context, layoutResource)

    override fun refreshContent(e: Entry, highlight: Highlight) {
        markerTv.text = "${e.data}\n${e.y.toInt()}"
    }

    override fun getOffset(): MPPointF =
        MPPointF((-width - 10).toFloat(), (-height - 10).toFloat())
}
