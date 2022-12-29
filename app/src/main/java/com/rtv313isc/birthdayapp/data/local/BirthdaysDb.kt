package com.rtv313isc.birthdayapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [LocalBirthDay::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class BirthdaysDb : RoomDatabase() {
    abstract val dao: BirthdaysDao
}