package com.sousajrps.covid19pt.dailyCases

import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.sousajrps.covid19pt.BaseActivity
import com.sousajrps.covid19pt.R

class DailyCasesActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_cases)

        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        val fragment: DailyCasesFragment = DailyCasesFragment.newInstance(isFullScreen = true)
        fragmentTransaction.add(R.id.fragment_container, fragment, "DailyCasesFragment")
        fragmentTransaction.commit()
    }
}
