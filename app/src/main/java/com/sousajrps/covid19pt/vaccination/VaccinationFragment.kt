package com.sousajrps.covid19pt.vaccination

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.sousajrps.covid19pt.R
import com.sousajrps.covid19pt.remote.models.Vaccination
import java.util.*
import kotlin.collections.ArrayList

class VaccinationFragment : Fragment() {
    private lateinit var viewModel: VaccinationViewModel
    private lateinit var textView: TextView
    private lateinit var pieChart: PieChart

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_vaccination, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textView = view.findViewById(R.id.textview_second)
        pieChart = view.findViewById(R.id.piechart)
        initViewModelAndObserve()
    }

    private fun initViewModelAndObserve() {
        viewModel = ViewModelProvider(this, VaccinationViewModelFactory())
            .get(VaccinationViewModel::class.java)
        viewModel.getData(Date().time)

        viewModel.data.observe(viewLifecycleOwner, { data ->
            Log.d("VaccinationFragment", "data: $data")
            textView.text = data.toString()
            setChartData(data)
        })

        viewModel.showLoading.observe(viewLifecycleOwner, { loading ->
            /*  if (loading) {
                  loadingView.visibility = View.VISIBLE
              } else {
                  loadingView.visibility = View.GONE
              }*/
        })
    }

    private fun setChartData(data: Vaccination) {
        val cenas: ArrayList<PieEntry> = ArrayList()
        val totalPopulacao = 10280000
        val firstdose = data.doses1
        val secondDose = data.doses2
        val naVacinados1 = totalPopulacao - firstdose
        val naVacinados2 = totalPopulacao - secondDose
        cenas.add(PieEntry(secondDose, 1))
        cenas.add(PieEntry(firstdose - secondDose, 2))
        cenas.add(PieEntry(naVacinados1, 3))

        val dataSet = PieDataSet(cenas, "Number Of Employees")
        val colors: ArrayList<Int> = ArrayList()

        for (c in ColorTemplate.VORDIPLOM_COLORS) colors.add(c)

        for (c in ColorTemplate.JOYFUL_COLORS) colors.add(c)

        for (c in ColorTemplate.COLORFUL_COLORS) colors.add(c)

        for (c in ColorTemplate.LIBERTY_COLORS) colors.add(c)

        for (c in ColorTemplate.PASTEL_COLORS) colors.add(c)

        colors.add(ColorTemplate.getHoloBlue())

        dataSet.colors = colors

        val cenasLabel: ArrayList<String> = ArrayList()
        cenasLabel.add("Primeira Dose")
        cenasLabel.add("Não vacinados")

        val data: PieData = PieData(dataSet)
        pieChart.data = data
        pieChart.animateXY(2000, 2000)
    }
}
