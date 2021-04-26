package com.sousajrps.covid19pt.local

import androidx.room.*
import io.reactivex.Single

@Dao
interface VaccinationDao {

    @Query("select * from vaccination")
    fun getAll(): Single<List<Vaccination>>

    @Query("SELECT * FROM vaccination ORDER BY data DESC LIMIT 1")
    fun getLast(): Vaccination

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vaccination: List<Vaccination>)

    @Delete
    fun delete(vaccination: Vaccination)
}
