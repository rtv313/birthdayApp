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

    @Query("SELECT * FROM birthdays WHERE r_birthday >= :currentDate ORDER BY r_birthday ASC")
    suspend fun getAllBirthdaysAfterToday(currentDate: Long): List<LocalBirthDay>

    @Query("SELECT * FROM birthdays WHERE r_birthday < :currentDate ORDER BY r_birthday ASC")
    suspend fun getAllBirthdaysBeforeToday(currentDate: Long): List<LocalBirthDay>
}