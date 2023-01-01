package com.rtv313isc.birthdayapp.domain

import android.content.Context
import com.rtv313isc.birthdayapp.data.LogoutRepository
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val repository: LogoutRepository
) {
    suspend operator fun invoke(activity: Context) {
        repository.logout(activity)
    }
}