package com.sousajrps.covid19pt.sharedPreferences

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.reactivex.Single
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

    override var covid19PtDataTimeStamp: Long
        get() = sharedPreferences.getLong(COVID_19_PT_DATA_TIME_STAMP, 0L)
        set(timeStamp) = sharedPreferences.edit().putLong(COVID_19_PT_DATA_TIME_STAMP, timeStamp)
            .apply()

    override var covid19PtData: List<Map<String, String>>
        get() = getRawData(COVID_19_PT_DATA)
        set(dataList) = setRawData(COVID_19_PT_DATA, dataList)

    override fun getLocalCovid19PtDataSingle(): Single<List<Map<String, String>>> =
        Single.create { emitter ->
            emitter.onSuccess(getRawData(COVID_19_PT_DATA))
        }

    override var covid19PtVaccinationTimeStamp: Long
        get() = sharedPreferences.getLong(COVID_19_PT_VACCINATION_TIME_STAMP, 0L)
        set(timeStamp) = sharedPreferences.edit()
            .putLong(COVID_19_PT_VACCINATION_TIME_STAMP, timeStamp)
            .apply()

    override var covid19PtVaccination: List<Map<String, String>>
        get() = getRawData(COVID_19_PT_VACCINATION)
        set(dataList) = setRawData(COVID_19_PT_VACCINATION, dataList)

    override fun getLocalCovid19PtVaccinationSingle(): Single<List<Map<String, String>>> =
        Single.create { emitter ->
            emitter.onSuccess(getRawData(COVID_19_PT_VACCINATION))
        }

    override var covid19PtVaccinationWeekly: List<Map<String, String>>
        get() = getRawData(COVID_19_PT_VACCINATION_WEEKLY)
        set(dataList) = setRawData(COVID_19_PT_VACCINATION_WEEKLY, dataList)

    override fun getLocalCovid19PtVaccinationWeeklySingle(): Single<List<Map<String, String>>> =
        Single.create { emitter ->
            emitter.onSuccess(getRawData(COVID_19_PT_VACCINATION_WEEKLY))
        }

    override var nightMode: String
        get() = sharedPreferences.getString(NIGHT_MODE, "").orEmpty()
        set(nightMode) = sharedPreferences.edit().putString(NIGHT_MODE, nightMode).apply()

    override var locale: Locale?
        get() = getLocaleFromString()
        set(locale) = setLocaleFromString(locale)

    private fun getRawData(key: String): List<Map<String, String>> {
        val map =
            Types.newParameterizedType(Map::class.java, String::class.java, String::class.java)
        val listMap = Types.newParameterizedType(List::class.java, map)
        val adapter: JsonAdapter<List<Map<String, String>>> = moshi.adapter(listMap)
        val json = sharedPreferences.getString(key, "")
        return json?.let { adapter.fromJson(it).orEmpty() } ?: emptyList()
    }

    private fun setRawData(key: String, matrixData: List<Map<String, String>>) {
        val map =
            Types.newParameterizedType(Map::class.java, String::class.java, String::class.java)
        val listMap = Types.newParameterizedType(List::class.java, map)
        val adapter: JsonAdapter<List<Map<String, String>>> = moshi.adapter(listMap)
        val json: String = adapter.toJson(matrixData)
        sharedPreferences.edit().putString(key, json).apply()
    }

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
        private const val SHARED_PREFERENCES_ENCRYPTED = "SharedPreferencesEncrypted"
        private const val COVID_19_PT_DATA = "covid19PtData"
        private const val COVID_19_PT_VACCINATION = "covid19PtVaccination"
        private const val COVID_19_PT_VACCINATION_WEEKLY = "covid19PtVaccinationWeekly"
        private const val COVID_19_PT_DATA_TIME_STAMP = "covid19PtDataTimeStamp"
        private const val NIGHT_MODE = "nightMode"
        private const val LOCALE = "locale"
        private const val COVID_19_PT_VACCINATION_TIME_STAMP = "covid19PtVaccinationTimeStamp"
    }
}
