package com.sousajrps.covid19pt

import android.content.Context
import android.content.ContextWrapper
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import android.os.Bundle
import android.os.LocaleList
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        val navController = findNavController(R.id.nav_host_fragment)
        val bottomBarView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.MatrixFragment,
                R.id.ReportFragment,
                R.id.VaccinationFragment,
                R.id.SettingsFragment
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        bottomBarView.setupWithNavController(navController)
    }

    override fun attachBaseContext(newBase: Context) {
        val language :String= if (AppModule.getAppSharedPreferences().locale.isEmpty()) {
            Log.d("SettingsFragment", "default language: ${Locale.getDefault().language}")
            Locale.getDefault().language
        } else {
            Log.d("SettingsFragment", "language: ${AppModule.getAppSharedPreferences().locale}")
            AppModule.getAppSharedPreferences().locale

        }
        val localeUpdatedContext: ContextWrapper = ContextUtils.updateLocale(newBase, Locale(language))
        super.attachBaseContext(localeUpdatedContext)
    }
}
