package com.arun.arunwhiterabbittest.apis

import com.arun.arunwhiterabbittest.db.EmployeeData
import retrofit2.Call
import retrofit2.http.GET

interface AppApis {
    @GET("v2/5d565297300000680030a986")
    fun getFullData(): Call<List<EmployeeData>>
}

