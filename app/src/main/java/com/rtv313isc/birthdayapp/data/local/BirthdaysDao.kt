package com.rtv313isc.birthdayapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BirthdaysDao {
    @Query("SELECT * FROM birthdays")
    suspend fun getAll(): List<LocalBirthDay>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAll(birthdays: List<LocalBirthDay>)
}