package com.rtv313isc.birthdayapp.domain

import com.rtv313isc.birthdayapp.data.BirthdaysRepository
import com.rtv313isc.birthdayapp.data.local.LocalBirthDay
import javax.inject.Inject

class GetBirthdaysUseCase @Inject constructor(
    private val repository: BirthdaysRepository
) {
    suspend operator fun invoke(): List<LocalBirthDay> {
        repository.loadBirthdays()
        repository.getBirthdays()
        return repository.getBirthdays()
    }
}