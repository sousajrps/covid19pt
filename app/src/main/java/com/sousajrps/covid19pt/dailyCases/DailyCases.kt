package com.sousajrps.covid19pt.dailyCases

import android.os.Parcel
import android.os.Parcelable

data class DailyCases(
    val date: String = "",
    val newCases: Float = 0F
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().orEmpty(),
        parcel.readFloat()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(date)
        parcel.writeFloat(newCases)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DailyCases> {
        override fun createFromParcel(parcel: Parcel): DailyCases {
            return DailyCases(parcel)
        }

        override fun newArray(size: Int): Array<DailyCases?> {
            return arrayOfNulls(size)
        }
    }
}

