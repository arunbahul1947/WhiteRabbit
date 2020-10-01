package com.arun.arunwhiterabbittest.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.arun.arunwhiterabbittest.R
import com.arun.arunwhiterabbittest.db.EmployeeData
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)


        val data = intent.getSerializableExtra("data") as EmployeeData


        tvName.text = data.name
        tvCompany.text = data.username

        Picasso.get()
            .load(data.profile_image)
            .into(imageView)

    }
}