package com.arun.arunwhiterabbittest.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.arun.arunwhiterabbittest.db.EmployeeRepository
import com.arun.arunwhiterabbittest.R
import com.arun.arunwhiterabbittest.adapters.AdapterEmployee
import com.arun.arunwhiterabbittest.db.EmployeeData
import com.arun.arunwhiterabbittest.db.EmployeeViewModel
import kotlinx.android.synthetic.main.activity_main.*

class FirstActivity : AppCompatActivity() {

    private lateinit var employeeViewModel: EmployeeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getDataFromDb("")

        tvSearch.setOnClickListener {
            if (edtSearch.text.toString() != "")
            {
                getDataFromDb(edtSearch.text.toString())
            }
        }
    }

    private fun getDataFromDb(keyWord :String) {
        employeeViewModel = ViewModelProviders.of(this).get(EmployeeViewModel::class.java)
        employeeViewModel.dataUpdate(keyWord).observe(this, Observer <List<EmployeeData>>{ listData ->
            if (listData == null) {
                return@Observer
            } else {
                if (!listData.isNullOrEmpty()) {
                    showDataToList(listData)
                } else {
                    val employeeRepository = EmployeeRepository(application)
                    employeeRepository.getDataFromApi(this)
                }
            }
        })
    }

    private fun showDataToList(list: List<EmployeeData>) {
        val dataList = list as ArrayList<EmployeeData>
        if(! dataList.isNullOrEmpty())
        {
            val adapterEmployee = AdapterEmployee(
                this@FirstActivity,
                dataList,
                object : AdapterEmployee.OnClickListener {
                    override fun onClick(data: EmployeeData) {
                        startActivity(
                            Intent(this@FirstActivity, SecondActivity::class.java)
                                .putExtra("data", data)
                        )
                    }
                })
            recyclerView.adapter = adapterEmployee
        }
    }

}