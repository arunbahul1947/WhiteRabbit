package com.arun.arunwhiterabbittest.db

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "EmployeeListData")
data class EmployeeData (

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id") var id: Int?,
    @ColumnInfo(name = "name") var name: String?,
    @ColumnInfo(name = "username") var username: String?,
    @ColumnInfo(name = "email") var email: String?,
    @ColumnInfo(name = "profile_image") var profile_image: String?

):Serializable