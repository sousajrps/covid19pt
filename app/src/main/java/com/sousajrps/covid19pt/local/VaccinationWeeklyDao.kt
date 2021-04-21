package com.sousajrps.covid19pt.local

import androidx.room.*
import io.reactivex.Single

@Dao
interface VaccinationWeeklyDao {

    @Query("select * from vaccinationWeekly")
    fun getAll(): Single<List<VaccinationWeekly>>

    @Query("SELECT * FROM vaccinationWeekly ORDER BY data DESC LIMIT 1")
    fun getLast(): Vaccination

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vaccinationWeekly: List<VaccinationWeekly>)

    @Delete
    fun delete(vaccinationWeekly: VaccinationWeekly)
}
