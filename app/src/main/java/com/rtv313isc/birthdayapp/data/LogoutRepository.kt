package com.rtv313isc.birthdayapp.data

import android.content.Context
import com.rtv313isc.birthdayapp.data.di.IoDispatcher
import com.rtv313isc.birthdayapp.data.remote.LogoutApiService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LogoutRepository @Inject constructor(
    private val restInterface: LogoutApiService,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
){
    suspend fun logout(activity: Context) {
        withContext(dispatcher){
            restInterface.facebookLogout(activity)
        }
    }
}