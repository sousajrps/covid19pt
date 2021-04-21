package com.sousajrps.covid19pt.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        Data::class,
        Vaccination::class,
        VaccinationWeekly::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getDataDao(): DataDao
    abstract fun getVaccinationDao(): VaccinationDao
    abstract fun getVaccinationWeeklyDao(): VaccinationWeeklyDao
}
