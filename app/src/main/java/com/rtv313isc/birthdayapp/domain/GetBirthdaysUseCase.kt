package com.rtv313isc.birthdayapp.domain

import com.rtv313isc.birthdayapp.data.BirthdaysRepository
import javax.inject.Inject

class GetBirthdaysUseCase @Inject constructor(
    private val repository: BirthdaysRepository
){
    suspend operator fun invoke(): List<Birthday> {
        return repository.getBirthdaysSorted()
    }
}