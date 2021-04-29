package com.sousajrps.covid19pt

import android.content.res.Configuration
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat
import androidx.fragment.app.Fragment
import com.google.firebase.messaging.FirebaseMessaging
import com.sousajrps.covid19pt.sharedPreferences.AppSharedPreferences
import java.util.*

class SettingsFragment : Fragment() {
    private lateinit var nightSwitch: SwitchCompat
    private lateinit var languageRadioGroup: RadioGroup
    private lateinit var portugueseRb: RadioButton
    private lateinit var englishRb: RadioButton
    private lateinit var versionTv: TextView
    private lateinit var statusDgsLink: TextView
    private lateinit var vaccinationDgsLink: TextView
    private lateinit var githubSourceLink: TextView
    private lateinit var githubDataLink: TextView
    private lateinit var flaticonLink: TextView
    private lateinit var dailyNotification: SwitchCompat

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
        versionTv = view.findViewById(R.id.version_tv)
        statusDgsLink = view.findViewById(R.id.dgs_report_tv)
        vaccinationDgsLink = view.findViewById(R.id.dgs_vaccination_tv)
        githubSourceLink = view.findViewById(R.id.github_code_tv)
        githubDataLink = view.findViewById(R.id.covid19pt_data_tv)
        flaticonLink = view.findViewById(R.id.flaticon_tv)
        dailyNotification = view.findViewById(R.id.daily_notification_switchButton)

        handleNightSwitch()
        handleNotification()
        handleLanguage()
        generateAbout()
    }

    private fun generateAbout() {
        statusDgsLink.movementMethod = LinkMovementMethod.getInstance()
        vaccinationDgsLink.movementMethod = LinkMovementMethod.getInstance()
        githubSourceLink.movementMethod = LinkMovementMethod.getInstance()
        githubDataLink.movementMethod = LinkMovementMethod.getInstance()
        flaticonLink.movementMethod = LinkMovementMethod.getInstance()

        versionTv.text = getString(
            R.string.settings_version,
            requireContext().packageManager.getPackageInfo(
                requireContext().packageName,
                0
            ).versionName
        )
    }

    private fun handleLanguage() {
        val currentLocale: String = requireActivity().resources.configuration.locale.toString()
        portugueseRb.isChecked = currentLocale.contains(AppSharedPreferences.LANGUAGE_PT)
        englishRb.isChecked = currentLocale.contains(AppSharedPreferences.LANGUAGE_EN)
        languageRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            val currentLocaleString: String =
                requireActivity().resources.configuration.locale.toString()
            when {
                checkedId == R.id.portuguese_rb && !currentLocaleString.contains(
                    AppSharedPreferences.LANGUAGE_PT
                ) -> {
                    AppModule.getAppSharedPreferences().locale = Locale(
                        AppSharedPreferences.LANGUAGE_PT,
                        AppSharedPreferences.COUNTRY_PT
                    )
                    requireActivity().recreate()
                }
                checkedId == R.id.english_rb && !currentLocaleString.contains(AppSharedPreferences.LANGUAGE_EN) -> {
                    AppModule.getAppSharedPreferences().locale = Locale(
                        AppSharedPreferences.LANGUAGE_EN,
                        AppSharedPreferences.COUNTRY_US
                    )
                    requireActivity().recreate()
                }
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

    private fun handleNotification() {
        dailyNotification.isChecked =
            AppModule.getAppSharedPreferences().dailyNotificationSubscribed
        dailyNotification.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                subscribeNotification()
            } else {
                unsubscribeNotification()
            }
        }
    }

    private fun subscribeNotification() {
        val firebaseMessaging = FirebaseMessaging.getInstance()
        firebaseMessaging.subscribeToTopic(getString(R.string.notification_topic_daily_report))
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    AppModule.getAppSharedPreferences().dailyNotificationSubscribed = true
                }
            }
    }

    private fun unsubscribeNotification() {
        val firebaseMessaging = FirebaseMessaging.getInstance()
        firebaseMessaging.unsubscribeFromTopic(getString(R.string.notification_topic_daily_report))
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    AppModule.getAppSharedPreferences().dailyNotificationSubscribed = false
                }
            }
    }

    private fun isDarkModeOn(): Boolean {
        val currentNightMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        return currentNightMode == Configuration.UI_MODE_NIGHT_YES
    }
}
