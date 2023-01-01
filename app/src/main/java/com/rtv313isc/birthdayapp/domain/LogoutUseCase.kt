package com.rtv313isc.birthdayapp.domain

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.rtv313isc.birthdayapp.data.LoginRepository
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val repository: LoginRepository
) {
    suspend operator fun invoke(activity: Context) {
        repository.logout(activity)
    }
}