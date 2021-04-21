package com.sousajrps.covid19pt.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Single

@Dao
interface VaccinationDao {

    @Query("select * from vaccination")
    fun getAll(): Single<List<Vaccination>>

    @Query("SELECT * FROM vaccination ORDER BY data DESC LIMIT 1")
    fun getLast(): Vaccination

    @Insert
    fun insertAll(vaccination: List<Vaccination>)

    @Delete
    fun delete(vaccination: Vaccination)
}
