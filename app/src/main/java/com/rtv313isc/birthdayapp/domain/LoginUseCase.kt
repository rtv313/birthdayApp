package com.rtv313isc.birthdayapp.domain

import androidx.appcompat.app.AppCompatActivity
import com.rtv313isc.birthdayapp.data.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val repository: LoginRepository) {
    suspend operator fun invoke(activity: AppCompatActivity) {
        repository.login(activity)
    }
}
