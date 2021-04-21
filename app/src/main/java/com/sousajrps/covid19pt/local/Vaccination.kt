package com.sousajrps.covid19pt.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Vaccination(
    @PrimaryKey
    val data: String = "",

    @ColumnInfo(name = "doses")
    val doses: Float = 0F,

    @ColumnInfo(name = "doses_novas")
    val doses_novas: Float = 0F,

    @ColumnInfo(name = "doses1")
    val doses1: Float = 0F,

    @ColumnInfo(name = "doses1_novas")
    val doses1_novas: Float = 0F,

    @ColumnInfo(name = "doses2")
    val doses2: Float = 0F,

    @ColumnInfo(name = "doses2_novas")
    val doses2_novas: Float = 0F,
)
