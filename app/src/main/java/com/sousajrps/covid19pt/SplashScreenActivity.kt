package com.sousajrps.covid19pt

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.sousajrps.covid19pt.sharedPreferences.AppSharedPreferences

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeRemoteConfigs()
        setNightMode()
        goToMainActivity()
    }

    private fun initializeRemoteConfigs() {
        AppModule.getRemoteConfigs().initialize()
    }

    private fun setNightMode() {
        val nightMode = AppModule.getAppSharedPreferences().nightMode
        val isDarkModeOn = isDarkModeOn()
        if (nightMode == AppSharedPreferences.MODE_NIGHT_YES && !isDarkModeOn) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
        if (nightMode == AppSharedPreferences.MODE_NIGHT_NO && isDarkModeOn) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    private fun goToMainActivity() {
        val intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun isDarkModeOn(): Boolean {
        val currentNightMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        return currentNightMode == Configuration.UI_MODE_NIGHT_YES
    }
}
