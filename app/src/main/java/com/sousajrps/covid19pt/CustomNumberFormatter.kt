package com.sousajrps.covid19pt

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

object CustomNumberFormatter {
    private const val pattern = "#,###.##"
    private val decimalFormat: DecimalFormat = DecimalFormat(pattern)

    init {
        val decimalFormatSymbols = DecimalFormatSymbols()
        decimalFormatSymbols.groupingSeparator = '.'
        decimalFormatSymbols.decimalSeparator = ','
        decimalFormat.decimalFormatSymbols = decimalFormatSymbols
    }

    fun format(value: Float): String = decimalFormat.format(value)
    fun format(value: Int): String = decimalFormat.format(value)
}
