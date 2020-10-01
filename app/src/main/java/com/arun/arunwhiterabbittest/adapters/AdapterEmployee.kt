package com.arun.arunwhiterabbittest.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.arun.arunwhiterabbittest.R
import com.arun.arunwhiterabbittest.db.EmployeeData
import com.squareup.picasso.Picasso

class AdapterEmployee (private val context: Context,
private val dataList: ArrayList<EmployeeData>,
private val callback: OnClickListener
) :
RecyclerView.Adapter<AdapterEmployee.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater =
            parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        return ViewHolder(
            inflater.inflate(
                R.layout.nod_employee_list,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return dataList.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataList[position]

        holder.tvName.text = data.name
        holder.tvCompany.text = data.username

        Picasso.get()
            .load(data.profile_image)
            .into(holder.imageView)

        holder.itemView.setOnClickListener{
            callback.onClick(data)
        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imageView : ImageView = itemView.findViewById(R.id.imageView)
        val tvName : TextView = itemView.findViewById(R.id.tvName)
        val tvCompany : TextView = itemView.findViewById(R.id.tvCompany)
    }

    interface OnClickListener {
        fun onClick(data: EmployeeData)
    }

}