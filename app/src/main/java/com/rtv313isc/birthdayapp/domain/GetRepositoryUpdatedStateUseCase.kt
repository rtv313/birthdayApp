package com.rtv313isc.birthdayapp.domain

import com.rtv313isc.birthdayapp.data.BirthdaysRepository
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class GetRepositoryUpdatedStateUseCase @Inject constructor(private val repository: BirthdaysRepository) {
    operator fun invoke(): StateFlow<Boolean> {
        return repository.birthdaysUpdated
    }
}