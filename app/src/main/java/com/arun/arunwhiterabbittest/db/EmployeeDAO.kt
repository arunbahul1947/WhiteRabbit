package com.arun.arunwhiterabbittest.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.arun.arunwhiterabbittest.db.EmployeeData

@Dao
interface EmployeeDAO {

    @Insert()
    fun insert(employeeListData: EmployeeData)

    @Query("SELECT * FROM EmployeeListData")
    fun loadAll(): LiveData<List<EmployeeData>>

    @Query("DELETE FROM EmployeeListData")
    fun deleteAll()

    @Query("SELECT * FROM EmployeeListData WHERE name LIKE :searchText LIMIT 50")
    fun getList(searchText: String): LiveData<List<EmployeeData>>
}