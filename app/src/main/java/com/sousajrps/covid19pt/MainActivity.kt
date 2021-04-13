package com.sousajrps.covid19pt

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : BaseActivity() {

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
}
