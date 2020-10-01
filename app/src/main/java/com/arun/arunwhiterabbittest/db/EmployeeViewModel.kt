package com.arun.arunwhiterabbittest.db

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class EmployeeViewModel(application: Application) : AndroidViewModel(application) {

    lateinit var data: LiveData<List<EmployeeData>>
    private val cityRepository: EmployeeRepository =
        EmployeeRepository(application)

    fun dataUpdate(value: String): LiveData<List<EmployeeData>> {
        this.data = cityRepository.getEmployeeListInfo(value)
        return this.data
    }
}