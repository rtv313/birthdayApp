package com.rtv313isc.birthdayapp.data.di

import android.content.Context
import androidx.room.Room
import com.facebook.AccessToken
import com.facebook.GraphRequest
import com.facebook.GraphResponse
import com.rtv313isc.birthdayapp.data.local.BirthdaysDao
import com.rtv313isc.birthdayapp.data.local.BirthdaysDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BirthdaysModule {

    @Provides
    fun provideRoomDao(database: BirthdaysDb): BirthdaysDao {
        return  database.dao
    }

    @Singleton
    @Provides
    fun provideRoomDatabase(@ApplicationContext appContext: Context): BirthdaysDb {
        return Room.databaseBuilder(
            appContext,
            BirthdaysDb::class.java,
            "birthdays_database"
        ).fallbackToDestructiveMigration().build()
    }
}