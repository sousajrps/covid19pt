package com.sousajrps.covid19pt.remote

import com.sousajrps.covid19pt.remote.models.AppConfigurations

interface RemoteConfigUtils {
    fun initialize()
    fun getAppConfigurations(): AppConfigurations
}
