package com.sousajrps.covid19pt.vaccination

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.sousajrps.covid19pt.CustomNumberFormatter
import com.sousajrps.covid19pt.R
import kotlin.math.roundToInt

class VaccinationReportWeeklyAdapter(
    private val context: Context,
    private val riskMatrixData: List<VaccinationByAgeGroup>,
) : androidx.recyclerview.widget.RecyclerView.Adapter<VaccinationReportWeeklyAdapter.DataViewHolder>() {

    private val customNumberFormatter: CustomNumberFormatter = CustomNumberFormatter

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.vaccination_weekly_report_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        if (riskMatrixData[holder.adapterPosition].ageGroup == AgeGroup.HEADER) {
            holder.labelTv.text = context.getString(R.string.vaccination_age)
            holder.firstDoseTv.text = context.getString(R.string.vaccination_1st_dose)
            holder.firstDosePercentageTv.text = "%"
            holder.secondDoseTv.text = context.getString(R.string.vaccination_2nd_dose)
            holder.secondDosePercentageTv.text = "%"
        } else {
            holder.labelTv.text = context.getString(riskMatrixData[holder.adapterPosition].label)
            holder.firstDoseTv.text =
                customNumberFormatter.format(riskMatrixData[holder.adapterPosition].firstDose.toInt())
            holder.firstDosePercentageTv.text =
                "${riskMatrixData[holder.adapterPosition].firstDosePercentage.roundToInt()}%"
            holder.secondDoseTv.text =
                customNumberFormatter.format(riskMatrixData[holder.adapterPosition].secondDose.toInt())
            holder.secondDosePercentageTv.text =
                "${riskMatrixData[holder.adapterPosition].secondDosePercentage.roundToInt()}%"
        }
    }

    override fun getItemCount(): Int {
        return riskMatrixData.size
    }

    class DataViewHolder(v: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(v) {
        internal val labelTv: TextView = v.findViewById(R.id.label_tv)
        internal val firstDoseTv: TextView = v.findViewById(R.id.first_dose_tv)
        internal val firstDosePercentageTv: TextView = v.findViewById(R.id.first_dose_percentage_tv)
        internal val secondDoseTv: TextView = v.findViewById(R.id.second_dose_tv)
        internal val secondDosePercentageTv: TextView =
            v.findViewById(R.id.second_dose_percentage_tv)
    }
}
