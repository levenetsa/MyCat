package com.lev.mycat.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    version = 1,
    entities = [
        CatEntity::class
    ]
)
abstract class CatDatabase : RoomDatabase() {

    abstract fun catDao(): CatDao

    companion object {
        private var INSTANCE: CatDatabase? = null

        fun getInstance(context: Context): CatDatabase? {
            if (INSTANCE == null) {
                synchronized(CatDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        CatDatabase::class.java, "cat.db"
                    ).build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}
