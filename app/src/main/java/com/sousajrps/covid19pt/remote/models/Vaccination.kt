package com.sousajrps.covid19pt.remote.models

data class Vaccination(
    val data: String = "",
    val doses: Float = 0F,
    val doses_novas: Float = 0F,
    val doses1: Float = 0F,
    val doses1_novas: Float = 0F,
    val doses2: Float = 0F,
    val doses2_novas: Float = 0F,
)
