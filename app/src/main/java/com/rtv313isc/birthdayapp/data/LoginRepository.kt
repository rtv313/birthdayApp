package com.rtv313isc.birthdayapp.data

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.rtv313isc.birthdayapp.data.di.IoDispatcher
import com.rtv313isc.birthdayapp.data.remote.LoginApiService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginRepository @Inject constructor(
    private val restInterface: LoginApiService,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
){
    suspend fun login(activity: AppCompatActivity) {
        withContext(dispatcher){
            restInterface.facebookLogin(activity)
        }
    }

    suspend fun logout(activity: Context) {
        withContext(dispatcher){
            restInterface.facebookLogout(activity)
        }
    }
}