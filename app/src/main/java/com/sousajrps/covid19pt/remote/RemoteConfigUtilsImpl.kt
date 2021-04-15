package com.sousajrps.covid19pt.remote

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.sousajrps.covid19pt.remote.models.AppConfigurations
import com.sousajrps.covid19pt.R
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class RemoteConfigUtilsImpl : RemoteConfigUtils {
    private val firebaseRemoteConfig = FirebaseRemoteConfig.getInstance()
    private val moshi: Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    override fun initialize() {
        firebaseRemoteConfig.apply {
            setDefaultsAsync(R.xml.remote_config_default)
            setConfigSettingsAsync(
                FirebaseRemoteConfigSettings.Builder()
                    .setFetchTimeoutInSeconds(5)
                    .build()
            )
        }
        firebaseRemoteConfig.fetchAndActivate()
    }

    override fun getAppConfigurations(): AppConfigurations {
        val adapter = moshi.adapter(AppConfigurations::class.java)
        val configuration = adapter.fromJson(firebaseRemoteConfig.getString(CONFIGURATION_KEY))
        return configuration ?: AppConfigurations()
    }

    companion object {
        private const val CONFIGURATION_KEY = "appConfigurations"
    }

}
