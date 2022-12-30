package com.rtv313isc.birthdayapp.data

import com.rtv313isc.birthdayapp.data.di.IoDispatcher
import com.rtv313isc.birthdayapp.data.local.BirthdaysDao
import com.rtv313isc.birthdayapp.data.remote.BirthdaysApiService
import com.rtv313isc.birthdayapp.domain.Birthday
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import java.net.ConnectException
import java.net.UnknownHostException
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BirthdaysRepository @Inject constructor(
    private val restInterface: BirthdaysApiService,
    private val birthdaysDao: BirthdaysDao,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {
    private val _birthdaysUpdated = MutableStateFlow(false)
    val birthdaysUpdated : StateFlow<Boolean> = _birthdaysUpdated

    suspend fun loadBirthdays() {
         withContext(dispatcher){
            try {
                restInterface.getBirthdays()
                restInterface.birthdayListState.collect {
                    birthdaysDao.addAll(it)
                    _birthdaysUpdated.value = true
                }
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

    suspend fun getBirthdays() : List<Birthday> {
        return withContext(dispatcher) {
            return@withContext birthdaysDao.getAll().map {
                Birthday(it.id,it.name,setYearDate(it.birthDay))
            }
        }
    }

    suspend fun getBirthdaysSorted(): List<Birthday> {
        return withContext(dispatcher) {
            val currentDate = Date()
            val birthdaysAfterToday = birthdaysDao.getAllBirthdaysAfterToday(currentDate.time).map {
                Birthday(it.id,it.name,setYearDate(it.birthDay))
            }
            val birthdaysBeforeToday = birthdaysDao.getAllBirthdaysBeforeToday(currentDate.time).map {
                Birthday(it.id,it.name,setYearDate(it.birthDay,true))
            }
            return@withContext listOf(birthdaysAfterToday, birthdaysBeforeToday).flatten()
        }
    }

    private fun setYearDate(date: Date, nextYear: Boolean = false): String {
        val dateFormat: DateFormat = SimpleDateFormat("dd/MM/yyyy")
        val currentYear = dateFormat.format(Date()).split("/")[2].toInt()
        val dateSplit = dateFormat.format(date).split("/")
        val day = dateSplit[0]
        val month = dateSplit[1]

        return if (nextYear) {
            val year = (currentYear + 1).toString()
            val birthday = "$year-$month-$day"
            val monthName = LocalDate.parse(birthday).month
            val dayName = LocalDate.parse(birthday).dayOfWeek
            "$dayName:$day /$monthName/ $year"
        }else {
            val birthday = "$currentYear-$month-$day"
            val monthName = LocalDate.parse(birthday).month
            val dayName = LocalDate.parse(birthday).dayOfWeek
            "$dayName:$day /$monthName/ $currentYear"
        }
    }
}