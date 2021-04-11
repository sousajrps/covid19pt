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
        get() = getCovid19PtDataList()
        set(dataList) = setCovid19PtList(dataList)

    private fun getCovid19PtDataList(): List<Map<String, String>> {
        val map =
            Types.newParameterizedType(Map::class.java, String::class.java, String::class.java)
        val listMap = Types.newParameterizedType(List::class.java, map)
        val adapter: JsonAdapter<List<Map<String, String>>> = moshi.adapter(listMap)
        val json = sharedPreferences.getString(COVID_19_PT_DATA, "")

        return json?.let { adapter.fromJson(it).orEmpty() } ?: emptyList()
    }

    private fun setCovid19PtList(matrixData: List<Map<String, String>>) {
        val map =
            Types.newParameterizedType(Map::class.java, String::class.java, String::class.java)
        val listMap = Types.newParameterizedType(List::class.java, map)
        val adapter: JsonAdapter<List<Map<String, String>>> = moshi.adapter(listMap)
        val json: String = adapter.toJson(matrixData)
        sharedPreferences.edit().putString(COVID_19_PT_DATA, json).apply()
    }

    override fun getLocalCovid19PtDataSingle(): Single<List<Map<String, String>>> =
        Single.create { emitter ->
            emitter.onSuccess(getCovid19PtDataList())
        }

    override var covid19PtVaccination: List<Map<String, String>>
        get() = getCovid19PtVaccinationList()
        set(dataList) = setCovid19PtVaccinationList(dataList)

    private fun getCovid19PtVaccinationList(): List<Map<String, String>> {
        val map =
            Types.newParameterizedType(Map::class.java, String::class.java, String::class.java)
        val listMap = Types.newParameterizedType(List::class.java, map)
        val adapter: JsonAdapter<List<Map<String, String>>> = moshi.adapter(listMap)
        val json = sharedPreferences.getString(COVID_19_PT_VACCINATION, "")

        return json?.let { adapter.fromJson(it).orEmpty() } ?: emptyList()
    }

    private fun setCovid19PtVaccinationList(matrixData: List<Map<String, String>>) {
        val map =
            Types.newParameterizedType(Map::class.java, String::class.java, String::class.java)
        val listMap = Types.newParameterizedType(List::class.java, map)
        val adapter: JsonAdapter<List<Map<String, String>>> = moshi.adapter(listMap)
        val json: String = adapter.toJson(matrixData)
        sharedPreferences.edit().putString(COVID_19_PT_VACCINATION, json).apply()
    }

    override fun getLocalCovid19PtVaccinationSingle(): Single<List<Map<String, String>>> =
        Single.create { emitter ->
            emitter.onSuccess(getCovid19PtVaccinationList())
        }

    override var nightMode: String
        get() = sharedPreferences.getString(NIGHT_MODE, "").orEmpty()
        set(nightMode) = sharedPreferences.edit().putString(NIGHT_MODE, nightMode).apply()

    override var locale: String
        get() = sharedPreferences.getString(LOCALE, "").orEmpty()
        set(nightMode) = sharedPreferences.edit().putString(LOCALE, nightMode).apply()

    override var covid19PtVaccinationTimeStamp: Long
        get() = sharedPreferences.getLong(COVID_19_PT_VACCINATION_TIME_STAMP, 0L)
        set(timeStamp) = sharedPreferences.edit().putLong(COVID_19_PT_VACCINATION_TIME_STAMP, timeStamp)
            .apply()

    companion object {
        private const val SHARED_PREFERENCES_ENCRYPTED = "SharedPreferencesEncrypted"
        private const val COVID_19_PT_DATA = "covid19PtData"
        private const val COVID_19_PT_VACCINATION = "covid19PtVaccination"
        private const val COVID_19_PT_DATA_TIME_STAMP = "covid19PtDataTimeStamp"
        private const val NIGHT_MODE = "nightMode"
        private const val LOCALE = "locale"
        private const val COVID_19_PT_VACCINATION_TIME_STAMP = "covid19PtVaccinationTimeStamp"
    }
}
