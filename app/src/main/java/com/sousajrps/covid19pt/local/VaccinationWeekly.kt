package com.sousajrps.covid19pt.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class VaccinationWeekly(
    @PrimaryKey
    val data: String = "",

    @ColumnInfo(name = "data_dados")
    val recebidas: Float = 0F,

    @ColumnInfo(name = "distribuidas")
    val distribuidas: Float = 0F,

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

    @ColumnInfo(name = "dosesunk")
    val dosesunk: Float = 0F,

    @ColumnInfo(name = "dosesunk_novas")
    val dosesunk_novas: Float = 0F,

    @ColumnInfo(name = "doses1_perc")
    val doses1_perc: Float = 0F,

    @ColumnInfo(name = "doses2_perc")
    val doses2_perc: Float = 0F,

    @ColumnInfo(name = "populacao1")
    val populacao1: Float = 0F,

    @ColumnInfo(name = "populacao2")
    val populacao2: Float = 0F,

    @ColumnInfo(name = "doses_continente")
    val doses_continente: Float = 0F,

    @ColumnInfo(name = "doses_novas_continente")
    val doses_novas_continente: Float = 0F,

    @ColumnInfo(name = "doses1_continente")
    val doses1_continente: Float = 0F,

    @ColumnInfo(name = "doses1_novas_continente")
    val doses1_novas_continente: Float = 0F,

    @ColumnInfo(name = "doses2_continente")
    val doses2_continente: Float = 0F,

    @ColumnInfo(name = "doses2_novas_continente")
    val doses2_novas_continente: Float = 0F,

    @ColumnInfo(name = "dosesunk_continente")
    val dosesunk_continente: Float = 0F,

    @ColumnInfo(name = "dosesunk_novas_continente")
    val dosesunk_novas_continente: Float = 0F,

    @ColumnInfo(name = "doses1_perc_continente")
    val doses1_perc_continente: Float = 0F,

    @ColumnInfo(name = "doses2_perc_continente")
    val doses2_perc_continente: Float = 0F,

    @ColumnInfo(name = "populacao1_continente")
    val populacao1_continente: Float = 0F,

    @ColumnInfo(name = "populacao2_continente")
    val populacao2_continente: Float = 0F,

    @ColumnInfo(name = "doses_arsnorte")
    val doses_arsnorte: Float = 0F,

    @ColumnInfo(name = "doses_novas_arsnorte")
    val doses_novas_arsnorte: Float = 0F,

    @ColumnInfo(name = "doses1_arsnorte")
    val doses1_arsnorte: Float = 0F,

    @ColumnInfo(name = "doses1_novas_arsnorte")
    val doses1_novas_arsnorte: Float = 0F,

    @ColumnInfo(name = "doses2_arsnorte")
    val doses2_arsnorte: Float = 0F,

    @ColumnInfo(name = "doses2_novas_arsnorte")
    val doses2_novas_arsnorte: Float = 0F,

    @ColumnInfo(name = "dosesunk_arsnorte")
    val dosesunk_arsnorte: Float = 0F,

    @ColumnInfo(name = "dosesunk_novas_arsnorte")
    val dosesunk_novas_arsnorte: Float = 0F,

    @ColumnInfo(name = "doses1_perc_arsnorte")
    val doses1_perc_arsnorte: Float = 0F,

    @ColumnInfo(name = "doses2_perc_arsnorte")
    val doses2_perc_arsnorte: Float = 0F,

    @ColumnInfo(name = "populacao1_arsnorte")
    val populacao1_arsnorte: Float = 0F,

    @ColumnInfo(name = "populacao2_arsnorte")
    val populacao2_arsnorte: Float = 0F,

    @ColumnInfo(name = "doses_arscentro")
    val doses_arscentro: Float = 0F,

    @ColumnInfo(name = "doses_novas_arscentro")
    val doses_novas_arscentro: Float = 0F,

    @ColumnInfo(name = "doses1_arscentro")
    val doses1_arscentro: Float = 0F,

    @ColumnInfo(name = "doses1_novas_arscentro")
    val doses1_novas_arscentro: Float = 0F,

    @ColumnInfo(name = "doses2_arscentro")
    val doses2_arscentro: Float = 0F,

    @ColumnInfo(name = "doses2_novas_arscentro")
    val doses2_novas_arscentro: Float = 0F,

    @ColumnInfo(name = "dosesunk_arscentro")
    val dosesunk_arscentro: Float = 0F,

    @ColumnInfo(name = "dosesunk_novas_arscentro")
    val dosesunk_novas_arscentro: Float = 0F,

    @ColumnInfo(name = "doses1_perc_arscentro")
    val doses1_perc_arscentro: Float = 0F,

    @ColumnInfo(name = "doses2_perc_arscentro")
    val doses2_perc_arscentro: Float = 0F,

    @ColumnInfo(name = "populacao1_arscentro")
    val populacao1_arscentro: Float = 0F,

    @ColumnInfo(name = "populacao2_arscentro")
    val populacao2_arscentro: Float = 0F,

    @ColumnInfo(name = "doses_arslvt")
    val doses_arslvt: Float = 0F,

    @ColumnInfo(name = "v")
    val doses_novas_arslvt: Float = 0F,

    @ColumnInfo(name = "doses1_arslvt")
    val doses1_arslvt: Float = 0F,

    @ColumnInfo(name = "doses1_novas_arslvt")
    val doses1_novas_arslvt: Float = 0F,

    @ColumnInfo(name = "doses2_arslvt")
    val doses2_arslvt: Float = 0F,

    @ColumnInfo(name = "doses2_novas_arslvt")
    val doses2_novas_arslvt: Float = 0F,

    @ColumnInfo(name = "dosesunk_arslvt")
    val dosesunk_arslvt: Float = 0F,

    @ColumnInfo(name = "dosesunk_novas_arslvt")
    val dosesunk_novas_arslvt: Float = 0F,

    @ColumnInfo(name = "doses1_perc_arslvt")
    val doses1_perc_arslvt: Float = 0F,

    @ColumnInfo(name = "doses2_perc_arslvt")
    val doses2_perc_arslvt: Float = 0F,

    @ColumnInfo(name = "populacao1_arslvt")
    val populacao1_arslvt: Float = 0F,

    @ColumnInfo(name = "populacao2_arslvt")
    val populacao2_arslvt: Float = 0F,

    @ColumnInfo(name = "doses_arsalentejo")
    val doses_arsalentejo: Float = 0F,

    @ColumnInfo(name = "doses_novas_arsalentejo")
    val doses_novas_arsalentejo: Float = 0F,

    @ColumnInfo(name = "doses1_arsalentejo")
    val doses1_arsalentejo: Float = 0F,

    @ColumnInfo(name = "doses1_novas_arsalentejo")
    val doses1_novas_arsalentejo: Float = 0F,

    @ColumnInfo(name = "doses2_arsalentejo")
    val doses2_arsalentejo: Float = 0F,

    @ColumnInfo(name = "doses2_novas_arsalentejo")
    val doses2_novas_arsalentejo: Float = 0F,

    @ColumnInfo(name = "dosesunk_arsalentejo")
    val dosesunk_arsalentejo: Float = 0F,

    @ColumnInfo(name = "dosesunk_novas_arsalentejo")
    val dosesunk_novas_arsalentejo: Float = 0F,

    @ColumnInfo(name = "doses1_perc_arsalentejo")
    val doses1_perc_arsalentejo: Float = 0F,

    @ColumnInfo(name = "doses2_perc_arsalentejo")
    val doses2_perc_arsalentejo: Float = 0F,

    @ColumnInfo(name = "populacao1_arsalentejo")
    val populacao1_arsalentejo: Float = 0F,

    @ColumnInfo(name = "populacao2_arsalentejo")
    val populacao2_arsalentejo: Float = 0F,

    @ColumnInfo(name = "doses_arsalgarve")
    val doses_arsalgarve: Float = 0F,

    @ColumnInfo(name = "doses_novas_arsalgarve")
    val doses_novas_arsalgarve: Float = 0F,

    @ColumnInfo(name = "doses1_arsalgarve")
    val doses1_arsalgarve: Float = 0F,

    @ColumnInfo(name = "doses1_novas_arsalgarve")
    val doses1_novas_arsalgarve: Float = 0F,

    @ColumnInfo(name = "doses2_arsalgarve")
    val doses2_arsalgarve: Float = 0F,

    @ColumnInfo(name = "doses2_novas_arsalgarve")
    val doses2_novas_arsalgarve: Float = 0F,

    @ColumnInfo(name = "dosesunk_arsalgarve")
    val dosesunk_arsalgarve: Float = 0F,

    @ColumnInfo(name = "dosesunk_novas_arsalgarve")
    val dosesunk_novas_arsalgarve: Float = 0F,

    @ColumnInfo(name = "doses1_perc_arsalgarve")
    val doses1_perc_arsalgarve: Float = 0F,

    @ColumnInfo(name = "doses2_perc_arsalgarve")
    val doses2_perc_arsalgarve: Float = 0F,

    @ColumnInfo(name = "populacao1_arsalgarve")
    val populacao1_arsalgarve: Float = 0F,

    @ColumnInfo(name = "populacao2_arsalgarve")
    val populacao2_arsalgarve: Float = 0F,

    @ColumnInfo(name = "doses_madeira")
    val doses_madeira: Float = 0F,

    @ColumnInfo(name = "doses_novas_madeira")
    val doses_novas_madeira: Float = 0F,

    @ColumnInfo(name = "doses1_madeira")
    val doses1_madeira: Float = 0F,

    @ColumnInfo(name = "doses1_novas_madeira")
    val doses1_novas_madeira: Float = 0F,

    @ColumnInfo(name = "doses2_madeira")
    val doses2_madeira: Float = 0F,

    @ColumnInfo(name = "doses2_novas_madeira")
    val doses2_novas_madeira: Float = 0F,

    @ColumnInfo(name = "dosesunk_madeira")
    val dosesunk_madeira: Float = 0F,

    @ColumnInfo(name = "dosesunk_novas_madeira")
    val dosesunk_novas_madeira: Float = 0F,

    @ColumnInfo(name = "doses1_perc_madeira")
    val doses1_perc_madeira: Float = 0F,

    @ColumnInfo(name = "doses2_perc_madeira")
    val doses2_perc_madeira: Float = 0F,

    @ColumnInfo(name = "populacao1_madeira")
    val populacao1_madeira: Float = 0F,

    @ColumnInfo(name = "populacao2_madeira")
    val populacao2_madeira: Float = 0F,

    @ColumnInfo(name = "doses_acores")
    val doses_acores: Float = 0F,

    @ColumnInfo(name = "doses_novas_acores")
    val doses_novas_acores: Float = 0F,

    @ColumnInfo(name = "doses1_acores")
    val doses1_acores: Float = 0F,

    @ColumnInfo(name = "doses1_novas_acores")
    val doses1_novas_acores: Float = 0F,

    @ColumnInfo(name = "doses2_acores")
    val doses2_acores: Float = 0F,

    @ColumnInfo(name = "doses2_novas_acores")
    val doses2_novas_acores: Float = 0F,

    @ColumnInfo(name = "dosesunk_acores")
    val dosesunk_acores: Float = 0F,

    @ColumnInfo(name = "dosesunk_novas_acores")
    val dosesunk_novas_acores: Float = 0F,

    @ColumnInfo(name = "doses1_perc_acores")
    val doses1_perc_acores: Float = 0F,

    @ColumnInfo(name = "doses2_perc_acores")
    val doses2_perc_acores: Float = 0F,

    @ColumnInfo(name = "populacao1_acores")
    val populacao1_acores: Float = 0F,

    @ColumnInfo(name = "populacao2_acores")
    val populacao2_acores: Float = 0F,

    @ColumnInfo(name = "doses_outro")
    val doses_outro: Float = 0F,

    @ColumnInfo(name = "doses_novas_outro")
    val doses_novas_outro: Float = 0F,

    @ColumnInfo(name = "doses1_outro")
    val doses1_outro: Float = 0F,

    @ColumnInfo(name = "doses1_novas_outro")
    val doses1_novas_outro: Float = 0F,

    @ColumnInfo(name = "doses2_outro")
    val doses2_outro: Float = 0F,

    @ColumnInfo(name = "doses2_novas_outro")
    val doses2_novas_outro: Float = 0F,

    @ColumnInfo(name = "dosesunk_outro")
    val dosesunk_outro: Float = 0F,

    @ColumnInfo(name = "dosesunk_novas_outro")
    val dosesunk_novas_outro: Float = 0F,

    @ColumnInfo(name = "doses_0_17")
    val doses_0_17: Float = 0F,

    @ColumnInfo(name = "doses_novas_0_17")
    val doses_novas_0_17: Float = 0F,

    @ColumnInfo(name = "doses1_0_17")
    val doses1_0_17: Float = 0F,

    @ColumnInfo(name = "doses1_novas_0_17")
    val doses1_novas_0_17: Float = 0F,

    @ColumnInfo(name = "doses2_0_17")
    val doses2_0_17: Float = 0F,

    @ColumnInfo(name = "doses2_novas_0_17")
    val doses2_novas_0_17: Float = 0F,

    @ColumnInfo(name = "dosesunk_0_17")
    val dosesunk_0_17: Float = 0F,

    @ColumnInfo(name = "dosesunk_novas_0_17")
    val dosesunk_novas_0_17: Float = 0F,

    @ColumnInfo(name = "doses1_perc_0_17")
    val doses1_perc_0_17: Float = 0F,

    @ColumnInfo(name = "doses2_perc_0_17")
    val doses2_perc_0_17: Float = 0F,

    @ColumnInfo(name = "populacao1_0_17")
    val populacao1_0_17: Float = 0F,

    @ColumnInfo(name = "populacao2_0_17")
    val populacao2_0_17: Float = 0F,

    @ColumnInfo(name = "doses_18_24")
    val doses_18_24: Float = 0F,

    @ColumnInfo(name = "doses_novas_18_24")
    val doses_novas_18_24: Float = 0F,

    @ColumnInfo(name = "doses1_18_24")
    val doses1_18_24: Float = 0F,

    @ColumnInfo(name = "doses1_novas_18_24")
    val doses1_novas_18_24: Float = 0F,

    @ColumnInfo(name = "doses2_18_24")
    val doses2_18_24: Float = 0F,

    @ColumnInfo(name = "doses2_novas_18_24")
    val doses2_novas_18_24: Float = 0F,

    @ColumnInfo(name = "dosesunk_18_24")
    val dosesunk_18_24: Float = 0F,

    @ColumnInfo(name = "dosesunk_novas_18_24")
    val dosesunk_novas_18_24: Float = 0F,

    @ColumnInfo(name = "doses1_perc_18_24")
    val doses1_perc_18_24: Float = 0F,

    @ColumnInfo(name = "doses2_perc_18_24")
    val doses2_perc_18_24: Float = 0F,

    @ColumnInfo(name = "populacao1_18_24")
    val populacao1_18_24: Float = 0F,

    @ColumnInfo(name = "populacao2_18_24")
    val populacao2_18_24: Float = 0F,

    @ColumnInfo(name = "doses_25_49")
    val doses_25_49: Float = 0F,

    @ColumnInfo(name = "doses_novas_25_49")
    val doses_novas_25_49: Float = 0F,

    @ColumnInfo(name = "doses1_25_49")
    val doses1_25_49: Float = 0F,

    @ColumnInfo(name = "doses1_novas_25_49")
    val doses1_novas_25_49: Float = 0F,

    @ColumnInfo(name = "doses2_25_49")
    val doses2_25_49: Float = 0F,

    @ColumnInfo(name = "doses2_novas_25_49")
    val doses2_novas_25_49: Float = 0F,

    @ColumnInfo(name = "dosesunk_25_49")
    val dosesunk_25_49: Float = 0F,

    @ColumnInfo(name = "dosesunk_novas_25_49")
    val dosesunk_novas_25_49: Float = 0F,

    @ColumnInfo(name = "doses1_perc_25_49")
    val doses1_perc_25_49: Float = 0F,

    @ColumnInfo(name = "doses2_perc_25_49")
    val doses2_perc_25_49: Float = 0F,

    @ColumnInfo(name = "populacao1_25_49")
    val populacao1_25_49: Float = 0F,

    @ColumnInfo(name = "populacao2_25_49")
    val populacao2_25_49: Float = 0F,

    @ColumnInfo(name = "doses_50_64")
    val doses_50_64: Float = 0F,

    @ColumnInfo(name = "doses_novas_50_64")
    val doses_novas_50_64: Float = 0F,

    @ColumnInfo(name = "doses1_50_64")
    val doses1_50_64: Float = 0F,

    @ColumnInfo(name = "doses1_novas_50_64")
    val doses1_novas_50_64: Float = 0F,

    @ColumnInfo(name = "doses2_50_64")
    val doses2_50_64: Float = 0F,

    @ColumnInfo(name = "doses2_novas_50_64")
    val doses2_novas_50_64: Float = 0F,

    @ColumnInfo(name = "dosesunk_50_64")
    val dosesunk_50_64: Float = 0F,

    @ColumnInfo(name = "dosesunk_novas_50_64")
    val dosesunk_novas_50_64: Float = 0F,

    @ColumnInfo(name = "doses1_perc_50_64")
    val doses1_perc_50_64: Float = 0F,

    @ColumnInfo(name = "doses2_perc_50_64")
    val doses2_perc_50_64: Float = 0F,

    @ColumnInfo(name = "populacao1_50_64")
    val populacao1_50_64: Float = 0F,

    @ColumnInfo(name = "populacao2_50_64")
    val populacao2_50_64: Float = 0F,

    @ColumnInfo(name = "doses_65_79")
    val doses_65_79: Float = 0F,

    @ColumnInfo(name = "doses_novas_65_79")
    val doses_novas_65_79: Float = 0F,

    @ColumnInfo(name = "doses1_65_79")
    val doses1_65_79: Float = 0F,

    @ColumnInfo(name = "doses1_novas_65_79")
    val doses1_novas_65_79: Float = 0F,

    @ColumnInfo(name = "doses2_65_79")
    val doses2_65_79: Float = 0F,

    @ColumnInfo(name = "doses2_novas_65_79")
    val doses2_novas_65_79: Float = 0F,

    @ColumnInfo(name = "dosesunk_65_79")
    val dosesunk_65_79: Float = 0F,

    @ColumnInfo(name = "dosesunk_novas_65_79")
    val dosesunk_novas_65_79: Float = 0F,

    @ColumnInfo(name = "doses1_perc_65_79")
    val doses1_perc_65_79: Float = 0F,

    @ColumnInfo(name = "doses2_perc_65_79")
    val doses2_perc_65_79: Float = 0F,

    @ColumnInfo(name = "populacao1_65_79")
    val populacao1_65_79: Float = 0F,

    @ColumnInfo(name = "populacao2_65_79")
    val populacao2_65_79: Float = 0F,

    @ColumnInfo(name = "doses_80")
    val doses_80: Float = 0F,

    @ColumnInfo(name = "doses_novas_80")
    val doses_novas_80: Float = 0F,

    @ColumnInfo(name = "doses1_80")
    val doses1_80: Float = 0F,

    @ColumnInfo(name = "doses1_novas_80")
    val doses1_novas_80: Float = 0F,

    @ColumnInfo(name = "doses2_80")
    val doses2_80: Float = 0F,

    @ColumnInfo(name = "doses2_novas_80")
    val doses2_novas_80: Float = 0F,

    @ColumnInfo(name = "dosesunk_80")
    val dosesunk_80: Float = 0F,

    @ColumnInfo(name = "dosesunk_novas_80")
    val dosesunk_novas_80: Float = 0F,

    @ColumnInfo(name = "doses1_perc_80")
    val doses1_perc_80: Float = 0F,

    @ColumnInfo(name = "doses2_perc_80")
    val doses2_perc_80: Float = 0F,

    @ColumnInfo(name = "populacao1_80")
    val populacao1_80: Float = 0F,

    @ColumnInfo(name = "populacao2_80")
    val populacao2_80: Float = 0F,

    @ColumnInfo(name = "doses_desconhecido")
    val doses_desconhecido: Float = 0F,

    @ColumnInfo(name = "doses_novas_desconhecido")
    val doses_novas_desconhecido: Float = 0F,

    @ColumnInfo(name = "doses1_desconhecido")
    val doses1_desconhecido: Float = 0F,

    @ColumnInfo(name = "doses1_novas_desconhecido")
    val doses1_novas_desconhecido: Float = 0F,

    @ColumnInfo(name = "doses2_desconhecido")
    val doses2_desconhecido: Float = 0F,

    @ColumnInfo(name = "doses2_novas_desconhecido")
    val doses2_novas_desconhecido: Float = 0F,

    @ColumnInfo(name = "dosesunk_desconhecido")
    val dosesunk_desconhecido: Float = 0F,

    @ColumnInfo(name = "dosesunk_novas_desconhecido")
    val dosesunk_novas_desconhecido: Float = 0F,
)
