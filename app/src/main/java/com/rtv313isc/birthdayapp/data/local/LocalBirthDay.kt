package com.rtv313isc.birthdayapp.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

@Entity(tableName = "birthdays")
data class LocalBirthDay(
    @PrimaryKey()
    @ColumnInfo(name = "r_id")
    val id: Long,
    @ColumnInfo(name = "r_name")
    val name: String,
    @ColumnInfo(name = "r_birthday")
    val birthDay: java.util.Date
)
