package com.rtv313isc.birthdayapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [LocalBirthDay::class],
    version = 1,
    exportSchema = false
)
abstract class BirthdaysDb : RoomDatabase() {
    abstract val dao: BirthdaysDao
}