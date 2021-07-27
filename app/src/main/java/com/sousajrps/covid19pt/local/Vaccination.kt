package com.sousajrps.covid19pt.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Vaccination(
    @PrimaryKey
    val data: String = "",

    @ColumnInfo(name = "doses")
    val doses: Double = 0.0,

    @ColumnInfo(name = "doses_novas")
    val doses_novas: Double = 0.0,

    @ColumnInfo(name = "doses1")
    val doses1: Double = 0.0,

    @ColumnInfo(name = "doses1_novas")
    val doses1_novas: Double = 0.0,

    @ColumnInfo(name = "doses2")
    val doses2: Double = 0.0,

    @ColumnInfo(name = "doses2_novas")
    val doses2_novas: Double = 0.0,

    @ColumnInfo(name = "pessoas_vacinadas_completamente")
    val pessoas_vacinadas_completamente: Double = 0.0,

    @ColumnInfo(name = "pessoas_vacinadas_completamente_novas")
    val pessoas_vacinadas_completamente_novas: Double = 0.0,

    @ColumnInfo(name = "pessoas_vacinadas_parcialmente")
    val pessoas_vacinadas_parcialmente: Double = 0.0,

    @ColumnInfo(name = "pessoas_vacinadas_parcialmente_novas")
    val pessoas_vacinadas_parcialmente_novas: Double = 0.0,

    @ColumnInfo(name = "pessoas_inoculadas")
    val pessoas_inoculadas: Double = 0.0,

    @ColumnInfo(name = "pessoas_inoculadas_novas")
    val pessoas_inoculadas_novas: Double = 0.0,

    @ColumnInfo(name = "vacinas")
    val vacinas: Double = 0.0,

    @ColumnInfo(name = "vacinas_novas")
    val vacinas_novas: Double = 0.0
)
