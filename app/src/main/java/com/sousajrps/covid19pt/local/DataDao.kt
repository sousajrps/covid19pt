package com.sousajrps.covid19pt.local

import androidx.room.*
import io.reactivex.Single

@Dao
interface DataDao {

    @Query("select * from data")
    fun getAll(): Single<List<Data>>

    @Query("SELECT * FROM data ORDER BY data DESC LIMIT 1")
    fun getLast(): Data

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(data: List<Data>)

    @Delete
    fun delete(data: Data)
}
