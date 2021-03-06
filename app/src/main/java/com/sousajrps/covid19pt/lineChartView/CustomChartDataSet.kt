package com.sousajrps.covid19pt.lineChartView

import android.os.Parcel
import android.os.Parcelable

class CustomChartDataSet : Parcelable {
    val label: Int
    val colorLines: Int
    val colorCircles: Int
    val customChartDataValues: List<CustomChartDataValue>

    constructor(
        label: Int,
        colorLines: Int,
        colorCircles: Int,
        dailyCases: List<CustomChartDataValue>
    ) {
        this.label = label
        this.colorLines = colorLines
        this.colorCircles = colorCircles
        this.customChartDataValues = dailyCases
    }

    constructor(parcel: Parcel) {
        label = parcel.readInt()
        colorLines = parcel.readInt()
        colorCircles = parcel.readInt()
        customChartDataValues = mutableListOf()
        parcel.readTypedList(customChartDataValues, CustomChartDataValue.CREATOR)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(label)
        parcel.writeInt(colorLines)
        parcel.writeInt(colorCircles)
        parcel.writeTypedList(customChartDataValues)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CustomChartDataSet> {
        override fun createFromParcel(parcel: Parcel): CustomChartDataSet {
            return CustomChartDataSet(parcel)
        }

        override fun newArray(size: Int): Array<CustomChartDataSet?> {
            return arrayOfNulls(size)
        }
    }
}
