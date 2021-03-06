package com.sousajrps.covid19pt.vaccination

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.sousajrps.covid19pt.CustomNumberFormatter
import com.sousajrps.covid19pt.R

class VaccinationReportAdapter(
    private val context: Context,
    private val riskMatrixData: List<VaccinationReportItem>,
) : androidx.recyclerview.widget.RecyclerView.Adapter<VaccinationReportAdapter.DataViewHolder>() {

    private val redColor = ContextCompat.getColor(context, R.color.red)
    private val greenColor = ContextCompat.getColor(context, R.color.green)
    private val customNumberFormatter: CustomNumberFormatter = CustomNumberFormatter

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.daily_report_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.labelTv.text = context.getString(riskMatrixData[holder.adapterPosition].labelRes)
        holder.totalTv.text =
            customNumberFormatter.format(riskMatrixData[holder.adapterPosition].vaccinationValue)
        holder.variationTv.text =
            customNumberFormatter.format(riskMatrixData[holder.adapterPosition].vaccinationVariation)
        holder.signalTv.text = riskMatrixData[holder.adapterPosition].vaccinationVariationSignal

        if (riskMatrixData[holder.adapterPosition].vaccinationItemColor == VaccinationItemColor.GREEN) {
            holder.signalTv.setTextColor(greenColor)
            holder.variationTv.setTextColor(greenColor)
        } else {
            holder.signalTv.setTextColor(redColor)
            holder.variationTv.setTextColor(redColor)
        }
    }

    override fun getItemCount(): Int {
        return riskMatrixData.size
    }

    class DataViewHolder(v: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(v) {
        internal val labelTv: TextView = v.findViewById(R.id.label_tv)
        internal val totalTv: TextView = v.findViewById(R.id.total_value_tv)
        internal val signalTv: TextView = v.findViewById(R.id.signal_tv)
        internal val variationTv: TextView = v.findViewById(R.id.day_value_tv)
    }
}
