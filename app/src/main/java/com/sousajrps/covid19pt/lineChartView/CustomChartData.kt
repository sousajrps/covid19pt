package com.sousajrps.covid19pt.lineChartView

import android.os.Parcel
import android.os.Parcelable
import com.sousajrps.covid19pt.R

class CustomChartData : Parcelable {
    val title: Int
    val sets: List<CustomChartDataSet>

    constructor() {
        title = R.string.app_name
        sets = emptyList()
    }

    constructor(title: Int, sets: List<CustomChartDataSet>) {
        this.title = title
        this.sets = sets
    }

    constructor(parcel: Parcel) {
        title = parcel.readInt()
        sets = mutableListOf()
        parcel.readTypedList(sets, CustomChartDataSet.CREATOR)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(title)
        parcel.writeTypedList(sets)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CustomChartData> {
        override fun createFromParcel(parcel: Parcel): CustomChartData {
            return CustomChartData(parcel)
        }

        override fun newArray(size: Int): Array<CustomChartData?> {
            return arrayOfNulls(size)
        }
    }
}
