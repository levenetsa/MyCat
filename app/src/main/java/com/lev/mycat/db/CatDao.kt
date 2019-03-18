package com.lev.mycat.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CatDao {

    @Query("SELECT * FROM cats")
    fun load(): List<CatEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(user: CatEntity)

    @Query("DELETE FROM cats")
    fun clear()
}