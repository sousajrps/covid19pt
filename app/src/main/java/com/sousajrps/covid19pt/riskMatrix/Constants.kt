package com.sousajrps.covid19pt.riskMatrix

import com.sousajrps.covid19pt.BuildConfig

const val cases_max = 240F
const val cases_middle = 120F
const val cases_min = 0F

const val rt_max = 2F
const val rt_middle = 1F
const val rt_min = 0F

const val HOURS_2 = (2 * 60 * 60 * 1000)
const val MINUTES_2 = (2 * 60 * 1000)
val TIME_OFFSET = if (BuildConfig.DEBUG) MINUTES_2 else HOURS_2
