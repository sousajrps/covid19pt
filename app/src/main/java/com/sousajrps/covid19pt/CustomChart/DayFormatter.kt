package com.sousajrps.covid19pt.CustomChart

import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.ValueFormatter
import java.text.SimpleDateFormat
import java.util.*

class DayFormatter : ValueFormatter() {

    override fun getFormattedValue(value: Float): String {
        return value.toString()
    }

    override fun getAxisLabel(value: Float, axis: AxisBase): String {
        val cl: Calendar = Calendar.getInstance()
        cl.timeInMillis = value.toLong()
        val format = SimpleDateFormat("dd-MM-yyyy")
        return format.format(cl.time)
    }
}

