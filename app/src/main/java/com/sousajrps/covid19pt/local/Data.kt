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
    val confirmados: Float = 0F,

    @ColumnInfo(name = "confirmados_arsnorte")
    val confirmados_arsnorte: Float = 0F,

    @ColumnInfo(name = "confirmados_arscentro")
    val confirmados_arscentro: Float = 0F,

    @ColumnInfo(name = "confirmados_arslvt")
    val confirmados_arslvt: Float = 0F,

    @ColumnInfo(name = "confirmados_arsalentejo")
    val confirmados_arsalentejo: Float = 0F,

    @ColumnInfo(name = "confirmados_arsalgarve")
    val confirmados_arsalgarve: Float = 0F,

    @ColumnInfo(name = "confirmados_acores")
    val confirmados_acores: Float = 0F,

    @ColumnInfo(name = "confirmados_madeira")
    val confirmados_madeira: Float = 0F,

    @ColumnInfo(name = "confirmados_estrangeiro")
    val confirmados_estrangeiro: Float = 0F,

    @ColumnInfo(name = "confirmados_novos")
    val confirmados_novos: Float = 0F,

    @ColumnInfo(name = "recuperados")
    val recuperados: Float = 0F,

    @ColumnInfo(name = "obitos")
    val obitos: Float = 0F,

    @ColumnInfo(name = "internados")
    val internados: Float = 0F,

    @ColumnInfo(name = "internados_uci")
    val internados_uci: Float = 0F,

    @ColumnInfo(name = "lab")
    val lab: Float = 0F,

    @ColumnInfo(name = "suspeitos")
    val suspeitos: Float = 0F,

    @ColumnInfo(name = "vigilancia")
    val vigilancia: Float = 0F,

    @ColumnInfo(name = "n_confirmados")
    val n_confirmados: Float = 0F,

    @ColumnInfo(name = "cadeias_transmissao")
    val cadeias_transmissao: Float = 0F,

    @ColumnInfo(name = "transmissao_importada")
    val transmissao_importada: Float = 0F,

    @ColumnInfo(name = "confirmados_0_9_f")
    val confirmados_0_9_f: Float = 0F,

    @ColumnInfo(name = "confirmados_0_9_m")
    val confirmados_0_9_m: Float = 0F,

    @ColumnInfo(name = "confirmados_10_19_f")
    val confirmados_10_19_f: Float = 0F,

    @ColumnInfo(name = "confirmados_10_19_m")
    val confirmados_10_19_m: Float = 0F,

    @ColumnInfo(name = "confirmados_20_29_f")
    val confirmados_20_29_f: Float = 0F,

    @ColumnInfo(name = "confirmados_20_29_m")
    val confirmados_20_29_m: Float = 0F,

    @ColumnInfo(name = "confirmados_30_39_f")
    val confirmados_30_39_f: Float = 0F,

    @ColumnInfo(name = "confirmados_30_39_m")
    val confirmados_30_39_m: Float = 0F,

    @ColumnInfo(name = "confirmados_40_49_f")
    val confirmados_40_49_f: Float = 0F,

    @ColumnInfo(name = "confirmados_40_49_m")
    val confirmados_40_49_m: Float = 0F,

    @ColumnInfo(name = "confirmados_50_59_f")
    val confirmados_50_59_f: Float = 0F,

    @ColumnInfo(name = "confirmados_50_59_m")
    val confirmados_50_59_m: Float = 0F,

    @ColumnInfo(name = "confirmados_60_69_f")
    val confirmados_60_69_f: Float = 0F,

    @ColumnInfo(name = "confirmados_60_69_m")
    val confirmados_60_69_m: Float = 0F,

    @ColumnInfo(name = "confirmados_70_79_f")
    val confirmados_70_79_f: Float = 0F,

    @ColumnInfo(name = "confirmados_70_79_m")
    val confirmados_70_79_m: Float = 0F,

    @ColumnInfo(name = "confirmados_80_plus_f")
    val confirmados_80_plus_f: Float = 0F,

    @ColumnInfo(name = "confirmados_80_plus_m")
    val confirmados_80_plus_m: Float = 0F,

    @ColumnInfo(name = "sintomas_tosse")
    val sintomas_tosse: Float = 0F,

    @ColumnInfo(name = "sintomas_febre")
    val sintomas_febre: Float = 0F,

    @ColumnInfo(name = "sintomas_dificuldade_respiratoria")
    val sintomas_dificuldade_respiratoria: Float = 0F,

    @ColumnInfo(name = "sintomas_cefaleia")
    val sintomas_cefaleia: Float = 0F,

    @ColumnInfo(name = "sintomas_dores_musculares")
    val sintomas_dores_musculares: Float = 0F,

    @ColumnInfo(name = "sintomas_fraqueza_generalizada")
    val sintomas_fraqueza_generalizada: Float = 0F,

    @ColumnInfo(name = "confirmados_f")
    val confirmados_f: Float = 0F,

    @ColumnInfo(name = "confirmados_m")
    val confirmados_m: Float = 0F,

    @ColumnInfo(name = "obitos_arsnorte")
    val obitos_arsnorte: Float = 0F,

    @ColumnInfo(name = "obitos_arscentro")
    val obitos_arscentro: Float = 0F,

    @ColumnInfo(name = "obitos_arslvt")
    val obitos_arslvt: Float = 0F,

    @ColumnInfo(name = "obitos_arsalentejo")
    val obitos_arsalentejo: Float = 0F,

    @ColumnInfo(name = "obitos_arsalgarve")
    val obitos_arsalgarve: Float = 0F,

    @ColumnInfo(name = "obitos_acores")
    val obitos_acores: Float = 0F,

    @ColumnInfo(name = "obitos_madeira")
    val obitos_madeira: Float = 0F,

    @ColumnInfo(name = "obitos_estrangeiro")
    val obitos_estrangeiro: Float = 0F,

    @ColumnInfo(name = "recuperados_arsnorte")
    val recuperados_arsnorte: Float = 0F,

    @ColumnInfo(name = "recuperados_arscentro")
    val recuperados_arscentro: Float = 0F,

    @ColumnInfo(name = "recuperados_arslvt")
    val recuperados_arslvt: Float = 0F,

    @ColumnInfo(name = "recuperados_arsalentejo")
    val recuperados_arsalentejo: Float = 0F,

    @ColumnInfo(name = "recuperados_arsalgarve")
    val recuperados_arsalgarve: Float = 0F,

    @ColumnInfo(name = "recuperados_acores")
    val recuperados_acores: Float = 0F,

    @ColumnInfo(name = "recuperados_madeira")
    val recuperados_madeira: Float = 0F,

    @ColumnInfo(name = "recuperados_estrangeiro")
    val recuperados_estrangeiro: Float = 0F,

    @ColumnInfo(name = "obitos_0_9_f")
    val obitos_0_9_f: Float = 0F,

    @ColumnInfo(name = "obitos_0_9_m")
    val obitos_0_9_m: Float = 0F,

    @ColumnInfo(name = "obitos_10_19_f")
    val obitos_10_19_f: Float = 0F,

    @ColumnInfo(name = "obitos_10_19_m")
    val obitos_10_19_m: Float = 0F,

    @ColumnInfo(name = "obitos_20_29_f")
    val obitos_20_29_f: Float = 0F,

    @ColumnInfo(name = "obitos_20_29_m")
    val obitos_20_29_m: Float = 0F,

    @ColumnInfo(name = "obitos_30_39_f")
    val obitos_30_39_f: Float = 0F,

    @ColumnInfo(name = "obitos_30_39_m")
    val obitos_30_39_m: Float = 0F,

    @ColumnInfo(name = "obitos_40_49_f")
    val obitos_40_49_f: Float = 0F,

    @ColumnInfo(name = "obitos_40_49_m")
    val obitos_40_49_m: Float = 0F,

    @ColumnInfo(name = "obitos_50_59_f")
    val obitos_50_59_f: Float = 0F,

    @ColumnInfo(name = "obitos_50_59_m")
    val obitos_50_59_m: Float = 0F,

    @ColumnInfo(name = "obitos_60_69_f")
    val obitos_60_69_f: Float = 0F,

    @ColumnInfo(name = "obitos_60_69_m")
    val obitos_60_69_m: Float = 0F,

    @ColumnInfo(name = "obitos_70_79_f")
    val obitos_70_79_f: Float = 0F,

    @ColumnInfo(name = "obitos_70_79_m")
    val obitos_70_79_m: Float = 0F,

    @ColumnInfo(name = "obitos_80_plus_f")
    val obitos_80_plus_f: Float = 0F,

    @ColumnInfo(name = "obitos_80_plus_m")
    val obitos_80_plus_m: Float = 0F,

    @ColumnInfo(name = "obitos_f")
    val obitos_f: Float = 0F,

    @ColumnInfo(name = "obitos_m")
    val obitos_m: Float = 0F,

    @ColumnInfo(name = "confirmados_desconhecidos_m")
    val confirmados_desconhecidos_m: Float = 0F,

    @ColumnInfo(name = "confirmados_desconhecidos_f")
    val confirmados_desconhecidos_f: Float = 0F,

    @ColumnInfo(name = "ativos")
    val ativos: Float = 0F,

    @ColumnInfo(name = "internados_enfermaria")
    val internados_enfermaria: Float = 0F,

    @ColumnInfo(name = "confirmados_desconhecidos")
    val confirmados_desconhecidos: Float = 0F,

    @ColumnInfo(name = "incidencia_nacional")
    val incidencia_nacional: Float = 0F,

    @ColumnInfo(name = "incidencia_continente")
    val incidencia_continente: Float = 0F,

    @ColumnInfo(name = "rt_nacional")
    val rt_nacional: Float = 0F,

    @ColumnInfo(name = "rt_continente")
    val rt_continente: Float = 0F,
)
