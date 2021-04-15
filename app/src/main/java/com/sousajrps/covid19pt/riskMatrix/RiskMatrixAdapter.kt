package com.sousajrps.covid19pt.riskMatrix

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.sousajrps.covid19pt.CustomNumberFormatter
import com.sousajrps.covid19pt.remote.models.MatrixParameters
import com.sousajrps.covid19pt.R
import com.sousajrps.covid19pt.riskMatrix.models.RiskMatrix

class RiskMatrixAdapter(
    private val context: Context,
    private val matrixParameters: MatrixParameters,
    private val riskMatrixData: List<RiskMatrix>,
) : androidx.recyclerview.widget.RecyclerView.Adapter<RiskMatrixAdapter.DataViewHolder>() {

    private val customNumberFormatter: CustomNumberFormatter = CustomNumberFormatter

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.matrix_row, parent, false)
        return DataViewHolder(v)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.dateTv.text = riskMatrixData[holder.adapterPosition].date
        holder.rtNationalTv.text =
            customNumberFormatter.format(riskMatrixData[holder.adapterPosition].rt_national)
        holder.incidenceNationalTv.text =
            customNumberFormatter.format(riskMatrixData[holder.adapterPosition].incidence_national)
        holder.rtContinentTv.text =
            customNumberFormatter.format(riskMatrixData[holder.adapterPosition].rt_continent)
        holder.incidenceContinentTv.text =
            customNumberFormatter.format(riskMatrixData[holder.adapterPosition].incidence_continent)
        holder.incidenceNationalTv.setTextColor(ContextCompat.getColor(context, R.color.textColor))
        holder.rtNationalTv.setTextColor(ContextCompat.getColor(context, R.color.textColor))
        holder.incidenceContinentTv.setTextColor(ContextCompat.getColor(context, R.color.textColor))
        holder.rtContinentTv.setTextColor(ContextCompat.getColor(context, R.color.textColor))

        if (riskMatrixData[holder.adapterPosition].rt_national >= matrixParameters.rtMiddle || riskMatrixData[holder.adapterPosition].incidence_national >= matrixParameters.casesMiddle) {
            holder.incidenceNationalTv.setTextColor(
                ContextCompat.getColor(
                    context,
                    R.color.honeyYellow
                )
            )
            holder.rtNationalTv.setTextColor(ContextCompat.getColor(context, R.color.honeyYellow))
        }
        if (riskMatrixData[holder.adapterPosition].rt_national >= matrixParameters.rtMiddle && riskMatrixData[holder.adapterPosition].incidence_national >= matrixParameters.casesMiddle) {
            holder.incidenceNationalTv.setTextColor(ContextCompat.getColor(context, R.color.red))
            holder.rtNationalTv.setTextColor(ContextCompat.getColor(context, R.color.red))
        }

        if (riskMatrixData[holder.adapterPosition].rt_continent >= matrixParameters.rtMiddle || riskMatrixData[holder.adapterPosition].incidence_continent >= matrixParameters.casesMiddle) {
            holder.incidenceContinentTv.setTextColor(
                ContextCompat.getColor(
                    context,
                    R.color.honeyYellow
                )
            )
            holder.rtContinentTv.setTextColor(ContextCompat.getColor(context, R.color.honeyYellow))
        }
        if (riskMatrixData[holder.adapterPosition].rt_continent >= matrixParameters.rtMiddle && riskMatrixData[holder.adapterPosition].incidence_continent >= matrixParameters.casesMiddle) {
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
