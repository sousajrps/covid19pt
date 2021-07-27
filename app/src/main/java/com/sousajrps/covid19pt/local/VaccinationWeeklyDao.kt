package com.sousajrps.covid19pt.local

import androidx.room.*
import io.reactivex.Single

@Dao
interface VaccinationWeeklyDao {

    @Query("select * from vaccinationWeekly")
    fun getAll(): Single<List<VaccinationWeekly>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vaccinationWeekly: List<VaccinationWeekly>)

    @Delete
    fun delete(vaccinationWeekly: VaccinationWeekly)
}
