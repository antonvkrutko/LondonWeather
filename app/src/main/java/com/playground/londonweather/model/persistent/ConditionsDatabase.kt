package com.playground.londonweather.model.persistent

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

/**
 * Created by Anton Krutko on 12/04/2018.
 *
 */
@Database(entities = arrayOf(ConditionsModel::class), version = 1, exportSchema = false)
abstract class ConditionsDatabase : RoomDatabase() {

    abstract val conditionsDao: ConditionsDao
}
