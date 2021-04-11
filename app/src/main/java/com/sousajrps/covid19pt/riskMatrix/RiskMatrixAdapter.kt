package com.sousajrps.covid19pt.riskMatrix

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.sousajrps.covid19pt.R
import com.sousajrps.covid19pt.riskMatrix.models.RiskMatrix

class RiskMatrixAdapter(
    private val context: Context,
    private val riskMatrixData: List<RiskMatrix>,
) : androidx.recyclerview.widget.RecyclerView.Adapter<RiskMatrixAdapter.DataViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.matrix_row, parent, false)
        return DataViewHolder(v)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.dateTv.text = riskMatrixData[holder.adapterPosition].date
        holder.rtNationalTv.text = riskMatrixData[holder.adapterPosition].rt_national.toString()
        holder.incidenceNationalTv.text =
            riskMatrixData[holder.adapterPosition].incidence_national.toString()
        holder.rtContinentTv.text = riskMatrixData[holder.adapterPosition].rt_continent.toString()
        holder.incidenceContinentTv.text =
            riskMatrixData[holder.adapterPosition].incidence_continent.toString()
        holder.incidenceNationalTv.setTextColor(ContextCompat.getColor(context, R.color.textColor))
        holder.rtNationalTv.setTextColor(ContextCompat.getColor(context, R.color.textColor))
        holder.incidenceContinentTv.setTextColor(ContextCompat.getColor(context, R.color.textColor))
        holder.rtContinentTv.setTextColor(ContextCompat.getColor(context, R.color.textColor))

        if (riskMatrixData[holder.adapterPosition].rt_national >= rt_middle || riskMatrixData[holder.adapterPosition].incidence_national >= cases_middle) {
            holder.incidenceNationalTv.setTextColor(
                ContextCompat.getColor(
                    context,
                    R.color.honeyYellow
                )
            )
            holder.rtNationalTv.setTextColor(ContextCompat.getColor(context, R.color.honeyYellow))
        }
        if (riskMatrixData[holder.adapterPosition].rt_national >= rt_middle && riskMatrixData[holder.adapterPosition].incidence_national >= cases_middle) {
            holder.incidenceNationalTv.setTextColor(ContextCompat.getColor(context, R.color.red))
            holder.rtNationalTv.setTextColor(ContextCompat.getColor(context, R.color.red))
        }

        if (riskMatrixData[holder.adapterPosition].rt_continent >= rt_middle || riskMatrixData[holder.adapterPosition].incidence_continent >= cases_middle) {
            holder.incidenceContinentTv.setTextColor(
                ContextCompat.getColor(
                    context,
                    R.color.honeyYellow
                )
            )
            holder.rtContinentTv.setTextColor(ContextCompat.getColor(context, R.color.honeyYellow))
        }
        if (riskMatrixData[holder.adapterPosition].rt_continent >= rt_middle && riskMatrixData[holder.adapterPosition].incidence_continent >= cases_middle) {
            holder.incidenceContinentTv.setTextColor(ContextCompat.getColor(context, R.color.red))
            holder.rtContinentTv.setTextColor(ContextCompat.getColor(context, R.color.red))
        }
    }

    override fun getItemCount(): Int {
        return riskMatrixData.size
    }

    class DataViewHolder(v: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(v) {
        internal val dateTv: TextView = v.findViewById(R.id.date)
        internal val rtNationalTv: TextView = v.findViewById(R.id.rt_national)
        internal val incidenceNationalTv: TextView = v.findViewById(R.id.incidence_national)
        internal val rtContinentTv: TextView = v.findViewById(R.id.rt_continent)
        internal val incidenceContinentTv: TextView = v.findViewById(R.id.incidence_continent)
    }
}
