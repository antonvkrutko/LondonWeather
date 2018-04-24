package com.playground.londonweather.model.persistent

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.reactivex.Maybe

/**
 * Created by Anton Krutko on 12/04/2018.
 *
 *
 */
@Dao
interface ConditionsDao {

    @Query("DELETE FROM ConditionsModel WHERE cityName = :city")
    fun delete(city: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(conditions: ConditionsModel)

    @Query("SELECT * FROM ConditionsModel WHERE cityName = :city")
    fun load(city: String): Maybe<ConditionsModel>
}
