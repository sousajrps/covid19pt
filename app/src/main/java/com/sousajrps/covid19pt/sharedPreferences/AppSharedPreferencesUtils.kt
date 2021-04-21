package com.sousajrps.covid19pt.sharedPreferences

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.util.*

class AppSharedPreferencesUtils : AppSharedPreferences {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var moshi: Moshi

    override fun initialize(context: Context) {
        moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            sharedPreferences = context.getSharedPreferences(
                SHARED_PREFERENCES_ENCRYPTED,
                Context.MODE_PRIVATE
            )
        } else {
            val mainKey = MasterKey.Builder(context)
                .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                .build()

            sharedPreferences = EncryptedSharedPreferences.create(
                context,
                SHARED_PREFERENCES_ENCRYPTED,
                mainKey,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )
        }
    }

    override fun clearAll() {
        sharedPreferences.edit().clear().apply()
    }

    override var sharedPreferencesVersion: Int
        get() = sharedPreferences.getInt(SHARED_PREFERENCES_VERSION, 0)
        set(version) = sharedPreferences.edit().putInt(SHARED_PREFERENCES_VERSION, version)
            .apply()

    override var dataTimeStamp: Long
        get() = sharedPreferences.getLong(DATA_TIME_STAMP, 0L)
        set(timeStamp) = sharedPreferences.edit().putLong(DATA_TIME_STAMP, timeStamp)
            .apply()

    override var vaccinationTimeStamp: Long
        get() = sharedPreferences.getLong(VACCINATION_TIME_STAMP, 0L)
        set(timeStamp) = sharedPreferences.edit()
            .putLong(VACCINATION_TIME_STAMP, timeStamp)
            .apply()

    override var vaccinationWeeklyTimeStamp: Long
        get() = sharedPreferences.getLong(VACCINATION_WEEKLY_TIME_STAMP, 0L)
        set(timeStamp) = sharedPreferences.edit()
            .putLong(VACCINATION_WEEKLY_TIME_STAMP, timeStamp)
            .apply()

    override var nightMode: String
        get() = sharedPreferences.getString(NIGHT_MODE, "").orEmpty()
        set(nightMode) = sharedPreferences.edit().putString(NIGHT_MODE, nightMode).apply()

    override var locale: Locale?
        get() = getLocaleFromString()
        set(locale) = setLocaleFromString(locale)

    private fun getLocaleFromString(): Locale? {
        val savedLocaleString = sharedPreferences.getString(LOCALE, "").orEmpty()
        if (savedLocaleString.isNotEmpty()) {
            var locale: Locale? = null
            try {
                val splittedLocale: List<String> = savedLocaleString.split('_')
                val savedLanguage: String = splittedLocale.first()
                val savedCountry: String = splittedLocale.last()
                if (savedLanguage.isNotEmpty() && savedCountry.isNotEmpty()) {
                    locale = Locale(savedLanguage, savedCountry)
                }
            } catch (e: Exception) {
                // no-opt
            }
            return locale
        } else {
            return null
        }
    }

    private fun setLocaleFromString(locale: Locale?) {
        val localeString: String = locale?.toString() ?: ""
        sharedPreferences.edit().putString(LOCALE, localeString).apply()
    }

    companion object {
        const val VERSION = 1
        private const val SHARED_PREFERENCES_ENCRYPTED = "SharedPreferencesEncrypted"
        private const val DATA_TIME_STAMP = "dataTimeStamp"
        private const val VACCINATION_TIME_STAMP = "vaccinationTimeStamp"
        private const val VACCINATION_WEEKLY_TIME_STAMP = "vaccinationWeeklyTimeStamp"
        private const val NIGHT_MODE = "nightMode"
        private const val LOCALE = "locale"
        private const val SHARED_PREFERENCES_VERSION = "sharedPreferencesVersion"
    }
}
