package com.rtv313isc.birthdayapp.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rtv313isc.birthdayapp.data.di.MainDispatcher
import com.rtv313isc.birthdayapp.domain.GetBirthdaysUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BirthdaysViewModel @Inject constructor(
    private val getBirthdaysUseCase: GetBirthdaysUseCase,
    @MainDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel(){

    private val errorHandler = CoroutineExceptionHandler { _, exception ->
        exception.printStackTrace()
    }

    init {
        getBirthdays()
    }

    private fun getBirthdays() {
        viewModelScope.launch (errorHandler + dispatcher){
            getBirthdaysUseCase()
        }
    }
}