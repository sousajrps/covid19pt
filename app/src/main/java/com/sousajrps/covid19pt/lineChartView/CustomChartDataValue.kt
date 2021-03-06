package com.sousajrps.covid19pt.lineChartView

import android.os.Parcel
import android.os.Parcelable

data class CustomChartDataValue(
    val date: String = "",
    val value: Double = 0.0
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().orEmpty(),
        parcel.readDouble()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(date)
        parcel.writeDouble(value)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CustomChartDataValue> {
        override fun createFromParcel(parcel: Parcel): CustomChartDataValue {
            return CustomChartDataValue(parcel)
        }

        override fun newArray(size: Int): Array<CustomChartDataValue?> {
            return arrayOfNulls(size)
        }
    }
}

