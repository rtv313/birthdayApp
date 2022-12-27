package com.rtv313isc.birthdayapp.data

import com.rtv313isc.birthdayapp.data.di.IoDispatcher
import com.rtv313isc.birthdayapp.data.local.BirthdaysDao
import com.rtv313isc.birthdayapp.data.local.LocalBirthDay
import com.rtv313isc.birthdayapp.data.remote.BirthdaysApiService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BirthdaysRepository @Inject constructor(
    private val restInterface: BirthdaysApiService,
    private val birthdaysDao: BirthdaysDao,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {

    suspend fun loadBirthdays() {
         withContext(dispatcher){
            try {
                restInterface.getBirthdays()
                restInterface.birthdayListState.collect { birthdaysDao.addAll(it) }
            }catch (e: Exception) {
                when (e) {
                    is UnknownHostException,
                    is ConnectException -> {
                        if (birthdaysDao.getAll().isEmpty())
                            throw Exception("Something went wrong. " + "We have no data.")
                    }
                    else -> throw e
                }
            }
        }
    }

    suspend fun getBirthdays() : List<LocalBirthDay> {
        return withContext(dispatcher) {
            return@withContext birthdaysDao.getAll()
        }
    }
}