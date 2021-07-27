package com.sousajrps.covid19pt.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class VaccinationWeekly(
    @PrimaryKey
    val data: String = "",

    @ColumnInfo(name = "data_dados")
    val recebidas: Double = 0.0,

    @ColumnInfo(name = "distribuidas")
    val distribuidas: Double = 0.0,

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

    @ColumnInfo(name = "doses1_perc")
    val doses1_perc: Double = 0.0,

    @ColumnInfo(name = "doses2_perc")
    val doses2_perc: Double = 0.0,

    @ColumnInfo(name = "populacao1")
    val populacao1: Double = 0.0,

    @ColumnInfo(name = "populacao2")
    val populacao2: Double = 0.0,

    @ColumnInfo(name = "doses_0_17")
    val doses_0_17: Double = 0.0,

    @ColumnInfo(name = "doses_novas_0_17")
    val doses_novas_0_17: Double = 0.0,

    @ColumnInfo(name = "doses1_0_17")
    val doses1_0_17: Double = 0.0,

    @ColumnInfo(name = "doses1_novas_0_17")
    val doses1_novas_0_17: Double = 0.0,

    @ColumnInfo(name = "doses2_0_17")
    val doses2_0_17: Double = 0.0,

    @ColumnInfo(name = "doses2_novas_0_17")
    val doses2_novas_0_17: Double = 0.0,

    @ColumnInfo(name = "dosesunk_0_17")
    val dosesunk_0_17: Double = 0.0,

    @ColumnInfo(name = "dosesunk_novas_0_17")
    val dosesunk_novas_0_17: Double = 0.0,

    @ColumnInfo(name = "doses1_perc_0_17")
    val doses1_perc_0_17: Double = 0.0,

    @ColumnInfo(name = "doses2_perc_0_17")
    val doses2_perc_0_17: Double = 0.0,

    @ColumnInfo(name = "populacao1_0_17")
    val populacao1_0_17: Double = 0.0,

    @ColumnInfo(name = "populacao2_0_17")
    val populacao2_0_17: Double = 0.0,

    @ColumnInfo(name = "doses_18_24")
    val doses_18_24: Double = 0.0,

    @ColumnInfo(name = "doses_novas_18_24")
    val doses_novas_18_24: Double = 0.0,

    @ColumnInfo(name = "doses1_18_24")
    val doses1_18_24: Double = 0.0,

    @ColumnInfo(name = "doses1_novas_18_24")
    val doses1_novas_18_24: Double = 0.0,

    @ColumnInfo(name = "doses2_18_24")
    val doses2_18_24: Double = 0.0,

    @ColumnInfo(name = "doses2_novas_18_24")
    val doses2_novas_18_24: Double = 0.0,

    @ColumnInfo(name = "dosesunk_18_24")
    val dosesunk_18_24: Double = 0.0,

    @ColumnInfo(name = "dosesunk_novas_18_24")
    val dosesunk_novas_18_24: Double = 0.0,

    @ColumnInfo(name = "doses1_perc_18_24")
    val doses1_perc_18_24: Double = 0.0,

    @ColumnInfo(name = "doses2_perc_18_24")
    val doses2_perc_18_24: Double = 0.0,

    @ColumnInfo(name = "populacao1_18_24")
    val populacao1_18_24: Double = 0.0,

    @ColumnInfo(name = "populacao2_18_24")
    val populacao2_18_24: Double = 0.0,

    @ColumnInfo(name = "doses_25_49")
    val doses_25_49: Double = 0.0,

    @ColumnInfo(name = "doses_novas_25_49")
    val doses_novas_25_49: Double = 0.0,

    @ColumnInfo(name = "doses1_25_49")
    val doses1_25_49: Double = 0.0,

    @ColumnInfo(name = "doses1_novas_25_49")
    val doses1_novas_25_49: Double = 0.0,

    @ColumnInfo(name = "doses2_25_49")
    val doses2_25_49: Double = 0.0,

    @ColumnInfo(name = "doses2_novas_25_49")
    val doses2_novas_25_49: Double = 0.0,

    @ColumnInfo(name = "dosesunk_25_49")
    val dosesunk_25_49: Double = 0.0,

    @ColumnInfo(name = "dosesunk_novas_25_49")
    val dosesunk_novas_25_49: Double = 0.0,

    @ColumnInfo(name = "doses1_perc_25_49")
    val doses1_perc_25_49: Double = 0.0,

    @ColumnInfo(name = "doses2_perc_25_49")
    val doses2_perc_25_49: Double = 0.0,

    @ColumnInfo(name = "populacao1_25_49")
    val populacao1_25_49: Double = 0.0,

    @ColumnInfo(name = "populacao2_25_49")
    val populacao2_25_49: Double = 0.0,

    @ColumnInfo(name = "doses_50_64")
    val doses_50_64: Double = 0.0,

    @ColumnInfo(name = "doses_novas_50_64")
    val doses_novas_50_64: Double = 0.0,

    @ColumnInfo(name = "doses1_50_64")
    val doses1_50_64: Double = 0.0,

    @ColumnInfo(name = "doses1_novas_50_64")
    val doses1_novas_50_64: Double = 0.0,

    @ColumnInfo(name = "doses2_50_64")
    val doses2_50_64: Double = 0.0,

    @ColumnInfo(name = "doses2_novas_50_64")
    val doses2_novas_50_64: Double = 0.0,

    @ColumnInfo(name = "dosesunk_50_64")
    val dosesunk_50_64: Double = 0.0,

    @ColumnInfo(name = "dosesunk_novas_50_64")
    val dosesunk_novas_50_64: Double = 0.0,

    @ColumnInfo(name = "doses1_perc_50_64")
    val doses1_perc_50_64: Double = 0.0,

    @ColumnInfo(name = "doses2_perc_50_64")
    val doses2_perc_50_64: Double = 0.0,

    @ColumnInfo(name = "populacao1_50_64")
    val populacao1_50_64: Double = 0.0,

    @ColumnInfo(name = "populacao2_50_64")
    val populacao2_50_64: Double = 0.0,

    @ColumnInfo(name = "doses_65_79")
    val doses_65_79: Double = 0.0,

    @ColumnInfo(name = "doses_novas_65_79")
    val doses_novas_65_79: Double = 0.0,

    @ColumnInfo(name = "doses1_65_79")
    val doses1_65_79: Double = 0.0,

    @ColumnInfo(name = "doses1_novas_65_79")
    val doses1_novas_65_79: Double = 0.0,

    @ColumnInfo(name = "doses2_65_79")
    val doses2_65_79: Double = 0.0,

    @ColumnInfo(name = "doses2_novas_65_79")
    val doses2_novas_65_79: Double = 0.0,

    @ColumnInfo(name = "dosesunk_65_79")
    val dosesunk_65_79: Double = 0.0,

    @ColumnInfo(name = "dosesunk_novas_65_79")
    val dosesunk_novas_65_79: Double = 0.0,

    @ColumnInfo(name = "doses1_perc_65_79")
    val doses1_perc_65_79: Double = 0.0,

    @ColumnInfo(name = "doses2_perc_65_79")
    val doses2_perc_65_79: Double = 0.0,

    @ColumnInfo(name = "populacao1_65_79")
    val populacao1_65_79: Double = 0.0,

    @ColumnInfo(name = "populacao2_65_79")
    val populacao2_65_79: Double = 0.0,

    @ColumnInfo(name = "doses_80")
    val doses_80: Double = 0.0,

    @ColumnInfo(name = "doses_novas_80")
    val doses_novas_80: Double = 0.0,

    @ColumnInfo(name = "doses1_80")
    val doses1_80: Double = 0.0,

    @ColumnInfo(name = "doses1_novas_80")
    val doses1_novas_80: Double = 0.0,

    @ColumnInfo(name = "doses2_80")
    val doses2_80: Double = 0.0,

    @ColumnInfo(name = "doses2_novas_80")
    val doses2_novas_80: Double = 0.0,

    @ColumnInfo(name = "dosesunk_80")
    val dosesunk_80: Double = 0.0,

    @ColumnInfo(name = "dosesunk_novas_80")
    val dosesunk_novas_80: Double = 0.0,

    @ColumnInfo(name = "doses1_perc_80")
    val doses1_perc_80: Double = 0.0,

    @ColumnInfo(name = "doses2_perc_80")
    val doses2_perc_80: Double = 0.0,

    @ColumnInfo(name = "populacao1_80")
    val populacao1_80: Double = 0.0,

    @ColumnInfo(name = "populacao2_80")
    val populacao2_80: Double = 0.0,

    @ColumnInfo(name = "doses_desconhecido")
    val doses_desconhecido: Double = 0.0,

    @ColumnInfo(name = "doses_novas_desconhecido")
    val doses_novas_desconhecido: Double = 0.0,

    @ColumnInfo(name = "doses1_desconhecido")
    val doses1_desconhecido: Double = 0.0,

    @ColumnInfo(name = "doses1_novas_desconhecido")
    val doses1_novas_desconhecido: Double = 0.0,

    @ColumnInfo(name = "doses2_desconhecido")
    val doses2_desconhecido: Double = 0.0,

    @ColumnInfo(name = "doses2_novas_desconhecido")
    val doses2_novas_desconhecido: Double = 0.0,

    @ColumnInfo(name = "dosesunk_desconhecido")
    val dosesunk_desconhecido: Double = 0.0,

    @ColumnInfo(name = "dosesunk_novas_desconhecido")
    val dosesunk_novas_desconhecido: Double = 0.0,
)
