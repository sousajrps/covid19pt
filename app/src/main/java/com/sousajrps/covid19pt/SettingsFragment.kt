package com.sousajrps.covid19pt

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat
import androidx.fragment.app.Fragment
import com.sousajrps.covid19pt.sharedPreferences.AppSharedPreferences

class SettingsFragment : Fragment() {
    private lateinit var nightSwitch: SwitchCompat
    private lateinit var languageRadioGroup: RadioGroup
    private lateinit var portugueseRb: RadioButton
    private lateinit var englishRb: RadioButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        languageRadioGroup = view.findViewById(R.id.language_rg)
        portugueseRb = view.findViewById(R.id.portuguese_rb)
        englishRb = view.findViewById(R.id.english_rb)
        nightSwitch = view.findViewById(R.id.night_mode_switchButton)

        handleNightSwitch()
        handleLanguage()
    }

    private fun handleLanguage() {
        val currentLocale = resources.configuration.locale.toString()
        portugueseRb.isChecked = currentLocale.contains(AppSharedPreferences.LOCALE_PT)
        englishRb.isChecked = !currentLocale.contains(AppSharedPreferences.LOCALE_PT)
        languageRadioGroup.setOnCheckedChangeListener { radioGroup, i ->
            if (i == R.id.portuguese_rb) {
                AppModule.getAppSharedPreferences().locale = AppSharedPreferences.LOCALE_PT
                requireActivity().recreate()
            }
            if (i == R.id.english_rb) {
                AppModule.getAppSharedPreferences().locale = AppSharedPreferences.LOCATE_EN
                requireActivity().recreate()
            }
        }
    }

    private fun handleNightSwitch() {
        nightSwitch.isChecked = isDarkModeOn()
        nightSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                AppModule.getAppSharedPreferences().nightMode = AppSharedPreferences.MODE_NIGHT_YES
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                AppModule.getAppSharedPreferences().nightMode = AppSharedPreferences.MODE_NIGHT_NO
            }
        }
    }

    private fun isDarkModeOn(): Boolean {
        val currentNightMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        return currentNightMode == Configuration.UI_MODE_NIGHT_YES
    }
}
