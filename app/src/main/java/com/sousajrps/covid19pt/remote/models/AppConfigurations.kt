package com.sousajrps.covid19pt.remote.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AppConfigurations(

    @Json(name = "matrixParameters")
    val matrixParameters: MatrixParameters = MatrixParameters(),

    @Json(name = "timeOffset")
    val timeOffset: Int = 7200000,

    @Json(name = "portuguesePopulation")
    val portuguesePopulation: Int = 10286263
)

data class MatrixParameters(
    @Json(name = "casesMax")
    val casesMax: Float = 240F,

    @Json(name = "casesMiddle")
    val casesMiddle: Float = 120F,

    @Json(name = "casesMin")
    val casesMin: Float = 0F,

    @Json(name = "rtMax")
    val rtMax: Float = 1.5F,

    @Json(name = "rtMiddle")
    val rtMiddle: Float = 1F,

    @Json(name = "rtMin")
    val rtMin: Float = 0.5F,
)
