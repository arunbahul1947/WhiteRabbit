package com.arun.arunwhiterabbittest.db

import android.app.Activity
import android.app.Application
import android.os.AsyncTask
import android.widget.Toast
import androidx.lifecycle.LiveData
import com.arun.arunwhiterabbittest.apis.ApiClient
import com.arun.arunwhiterabbittest.apis.AppApis
import com.arun.arunwhiterabbittest.db.EmployeeData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EmployeeRepository (application: Application) {

    private var employeeDAO: EmployeeDAO

    init {
        val db = DatabaseClass.getDatabase(application)
        employeeDAO = db?.employeeDao()!!
    }

    fun getEmployeeListInfo(query: String): LiveData<List<EmployeeData>> {
        return if(query.isEmpty())
            employeeDAO.loadAll()
        else
            employeeDAO.getList(query)
    }

    fun getDataFromApi(context : Activity) {
        val apiCall: AppApis = ApiClient.client.create(AppApis::class.java)
        apiCall.getFullData().enqueue(object : Callback<List<EmployeeData>> {
            override fun onFailure(call: Call<List<EmployeeData>>, t: Throwable) {
                Toast.makeText(context,"Api error occurred", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<EmployeeData>>, response: Response<List<EmployeeData>>) {
                if (response.isSuccessful) {
                        InsertAsyncTask(
                            employeeDAO
                        ).execute(response.body())
                } else
                    Toast.makeText(context,"Api error occurred", Toast.LENGTH_SHORT).show()
            }
        })
    }


    private class InsertAsyncTask internal constructor (private val employeeDAO: EmployeeDAO)
        : AsyncTask<List<EmployeeData>, Void, Void>() {
        override fun doInBackground(vararg list: List<EmployeeData>): Void? {
            employeeDAO.deleteAll()
            for (itemData in list[0]) {
                employeeDAO.insert(itemData)
            }
            return null
        }

    }
}
