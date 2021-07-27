package com.sousajrps.covid19pt.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Data(
    @PrimaryKey
    val data: String = "",

    @ColumnInfo(name = "data_dados")
    val data_dados: String = "",

    @ColumnInfo(name = "confirmados")
    val confirmados: Double = 0.0,

    @ColumnInfo(name = "confirmados_arsnorte")
    val confirmados_arsnorte: Double = 0.0,

    @ColumnInfo(name = "confirmados_arscentro")
    val confirmados_arscentro: Double = 0.0,

    @ColumnInfo(name = "confirmados_arslvt")
    val confirmados_arslvt: Double = 0.0,

    @ColumnInfo(name = "confirmados_arsalentejo")
    val confirmados_arsalentejo: Double = 0.0,

    @ColumnInfo(name = "confirmados_arsalgarve")
    val confirmados_arsalgarve: Double = 0.0,

    @ColumnInfo(name = "confirmados_acores")
    val confirmados_acores: Double = 0.0,

    @ColumnInfo(name = "confirmados_madeira")
    val confirmados_madeira: Double = 0.0,

    @ColumnInfo(name = "confirmados_estrangeiro")
    val confirmados_estrangeiro: Double = 0.0,

    @ColumnInfo(name = "confirmados_novos")
    val confirmados_novos: Double = 0.0,

    @ColumnInfo(name = "recuperados")
    val recuperados: Double = 0.0,

    @ColumnInfo(name = "obitos")
    val obitos: Double = 0.0,

    @ColumnInfo(name = "internados")
    val internados: Double = 0.0,

    @ColumnInfo(name = "internados_uci")
    val internados_uci: Double = 0.0,

    @ColumnInfo(name = "lab")
    val lab: Double = 0.0,

    @ColumnInfo(name = "suspeitos")
    val suspeitos: Double = 0.0,

    @ColumnInfo(name = "vigilancia")
    val vigilancia: Double = 0.0,

    @ColumnInfo(name = "n_confirmados")
    val n_confirmados: Double = 0.0,

    @ColumnInfo(name = "cadeias_transmissao")
    val cadeias_transmissao: Double = 0.0,

    @ColumnInfo(name = "transmissao_importada")
    val transmissao_importada: Double = 0.0,

    @ColumnInfo(name = "confirmados_0_9_f")
    val confirmados_0_9_f: Double = 0.0,

    @ColumnInfo(name = "confirmados_0_9_m")
    val confirmados_0_9_m: Double = 0.0,

    @ColumnInfo(name = "confirmados_10_19_f")
    val confirmados_10_19_f: Double = 0.0,

    @ColumnInfo(name = "confirmados_10_19_m")
    val confirmados_10_19_m: Double = 0.0,

    @ColumnInfo(name = "confirmados_20_29_f")
    val confirmados_20_29_f: Double = 0.0,

    @ColumnInfo(name = "confirmados_20_29_m")
    val confirmados_20_29_m: Double = 0.0,

    @ColumnInfo(name = "confirmados_30_39_f")
    val confirmados_30_39_f: Double = 0.0,

    @ColumnInfo(name = "confirmados_30_39_m")
    val confirmados_30_39_m: Double = 0.0,

    @ColumnInfo(name = "confirmados_40_49_f")
    val confirmados_40_49_f: Double = 0.0,

    @ColumnInfo(name = "confirmados_40_49_m")
    val confirmados_40_49_m: Double = 0.0,

    @ColumnInfo(name = "confirmados_50_59_f")
    val confirmados_50_59_f: Double = 0.0,

    @ColumnInfo(name = "confirmados_50_59_m")
    val confirmados_50_59_m: Double = 0.0,

    @ColumnInfo(name = "confirmados_60_69_f")
    val confirmados_60_69_f: Double = 0.0,

    @ColumnInfo(name = "confirmados_60_69_m")
    val confirmados_60_69_m: Double = 0.0,

    @ColumnInfo(name = "confirmados_70_79_f")
    val confirmados_70_79_f: Double = 0.0,

    @ColumnInfo(name = "confirmados_70_79_m")
    val confirmados_70_79_m: Double = 0.0,

    @ColumnInfo(name = "confirmados_80_plus_f")
    val confirmados_80_plus_f: Double = 0.0,

    @ColumnInfo(name = "confirmados_80_plus_m")
    val confirmados_80_plus_m: Double = 0.0,

    @ColumnInfo(name = "sintomas_tosse")
    val sintomas_tosse: Double = 0.0,

    @ColumnInfo(name = "sintomas_febre")
    val sintomas_febre: Double = 0.0,

    @ColumnInfo(name = "sintomas_dificuldade_respiratoria")
    val sintomas_dificuldade_respiratoria: Double = 0.0,

    @ColumnInfo(name = "sintomas_cefaleia")
    val sintomas_cefaleia: Double = 0.0,

    @ColumnInfo(name = "sintomas_dores_musculares")
    val sintomas_dores_musculares: Double = 0.0,

    @ColumnInfo(name = "sintomas_fraqueza_generalizada")
    val sintomas_fraqueza_generalizada: Double = 0.0,

    @ColumnInfo(name = "confirmados_f")
    val confirmados_f: Double = 0.0,

    @ColumnInfo(name = "confirmados_m")
    val confirmados_m: Double = 0.0,

    @ColumnInfo(name = "obitos_arsnorte")
    val obitos_arsnorte: Double = 0.0,

    @ColumnInfo(name = "obitos_arscentro")
    val obitos_arscentro: Double = 0.0,

    @ColumnInfo(name = "obitos_arslvt")
    val obitos_arslvt: Double = 0.0,

    @ColumnInfo(name = "obitos_arsalentejo")
    val obitos_arsalentejo: Double = 0.0,

    @ColumnInfo(name = "obitos_arsalgarve")
    val obitos_arsalgarve: Double = 0.0,

    @ColumnInfo(name = "obitos_acores")
    val obitos_acores: Double = 0.0,

    @ColumnInfo(name = "obitos_madeira")
    val obitos_madeira: Double = 0.0,

    @ColumnInfo(name = "obitos_estrangeiro")
    val obitos_estrangeiro: Double = 0.0,

    @ColumnInfo(name = "recuperados_arsnorte")
    val recuperados_arsnorte: Double = 0.0,

    @ColumnInfo(name = "recuperados_arscentro")
    val recuperados_arscentro: Double = 0.0,

    @ColumnInfo(name = "recuperados_arslvt")
    val recuperados_arslvt: Double = 0.0,

    @ColumnInfo(name = "recuperados_arsalentejo")
    val recuperados_arsalentejo: Double = 0.0,

    @ColumnInfo(name = "recuperados_arsalgarve")
    val recuperados_arsalgarve: Double = 0.0,

    @ColumnInfo(name = "recuperados_acores")
    val recuperados_acores: Double = 0.0,

    @ColumnInfo(name = "recuperados_madeira")
    val recuperados_madeira: Double = 0.0,

    @ColumnInfo(name = "recuperados_estrangeiro")
    val recuperados_estrangeiro: Double = 0.0,

    @ColumnInfo(name = "obitos_0_9_f")
    val obitos_0_9_f: Double = 0.0,

    @ColumnInfo(name = "obitos_0_9_m")
    val obitos_0_9_m: Double = 0.0,

    @ColumnInfo(name = "obitos_10_19_f")
    val obitos_10_19_f: Double = 0.0,

    @ColumnInfo(name = "obitos_10_19_m")
    val obitos_10_19_m: Double = 0.0,

    @ColumnInfo(name = "obitos_20_29_f")
    val obitos_20_29_f: Double = 0.0,

    @ColumnInfo(name = "obitos_20_29_m")
    val obitos_20_29_m: Double = 0.0,

    @ColumnInfo(name = "obitos_30_39_f")
    val obitos_30_39_f: Double = 0.0,

    @ColumnInfo(name = "obitos_30_39_m")
    val obitos_30_39_m: Double = 0.0,

    @ColumnInfo(name = "obitos_40_49_f")
    val obitos_40_49_f: Double = 0.0,

    @ColumnInfo(name = "obitos_40_49_m")
    val obitos_40_49_m: Double = 0.0,

    @ColumnInfo(name = "obitos_50_59_f")
    val obitos_50_59_f: Double = 0.0,

    @ColumnInfo(name = "obitos_50_59_m")
    val obitos_50_59_m: Double = 0.0,

    @ColumnInfo(name = "obitos_60_69_f")
    val obitos_60_69_f: Double = 0.0,

    @ColumnInfo(name = "obitos_60_69_m")
    val obitos_60_69_m: Double = 0.0,

    @ColumnInfo(name = "obitos_70_79_f")
    val obitos_70_79_f: Double = 0.0,

    @ColumnInfo(name = "obitos_70_79_m")
    val obitos_70_79_m: Double = 0.0,

    @ColumnInfo(name = "obitos_80_plus_f")
    val obitos_80_plus_f: Double = 0.0,

    @ColumnInfo(name = "obitos_80_plus_m")
    val obitos_80_plus_m: Double = 0.0,

    @ColumnInfo(name = "obitos_f")
    val obitos_f: Double = 0.0,

    @ColumnInfo(name = "obitos_m")
    val obitos_m: Double = 0.0,

    @ColumnInfo(name = "confirmados_desconhecidos_m")
    val confirmados_desconhecidos_m: Double = 0.0,

    @ColumnInfo(name = "confirmados_desconhecidos_f")
    val confirmados_desconhecidos_f: Double = 0.0,

    @ColumnInfo(name = "ativos")
    val ativos: Double = 0.0,

    @ColumnInfo(name = "internados_enfermaria")
    val internados_enfermaria: Double = 0.0,

    @ColumnInfo(name = "confirmados_desconhecidos")
    val confirmados_desconhecidos: Double = 0.0,

    @ColumnInfo(name = "incidencia_nacional")
    val incidencia_nacional: Double = 0.0,

    @ColumnInfo(name = "incidencia_continente")
    val incidencia_continente: Double = 0.0,

    @ColumnInfo(name = "rt_nacional")
    val rt_nacional: Double = 0.0,

    @ColumnInfo(name = "rt_continente")
    val rt_continente: Double = 0.0,
)
