package com.arun.arunwhiterabbittest.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [EmployeeData::class], version = 1)
abstract class DatabaseClass : RoomDatabase(){
    abstract fun employeeDao(): EmployeeDAO
    companion object {
        @Volatile
        private var INSTANCE: DatabaseClass? = null

        internal fun getDatabase(context: Context): DatabaseClass? {
            if (INSTANCE == null) {
                synchronized(DatabaseClass::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            DatabaseClass::class.java, "app_database"
                        )
                            .build()
                    }
                }
            }
            return INSTANCE
        }
    }
}