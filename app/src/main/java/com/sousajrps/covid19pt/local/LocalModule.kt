package com.sousajrps.covid19pt.local

import android.content.Context
import androidx.room.Room

object LocalModule {
    private const val APP_DATABASE_NAME = "matrix-risco-database"

    private lateinit var appDatabase: AppDatabase

    fun initializeDatabase(context: Context) {
        appDatabase = Room.databaseBuilder(
            context,
            AppDatabase::class.java, APP_DATABASE_NAME
        ).build()
    }

    fun getDatabase() = appDatabase

    fun getDataDao() = appDatabase.getDataDao()
    fun getVaccinationDao() = appDatabase.getVaccinationDao()
    fun getVaccinationWeeklyDao() = appDatabase.getVaccinationWeeklyDao()
}
