package com.rtv313isc.birthdayapp.domain

import com.rtv313isc.birthdayapp.data.BirthdaysRepository
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class RefreshBirthdaysUseCase @Inject constructor(private val repository: BirthdaysRepository) {
    suspend operator fun invoke() {
        repository.loadBirthdays()
    }
}