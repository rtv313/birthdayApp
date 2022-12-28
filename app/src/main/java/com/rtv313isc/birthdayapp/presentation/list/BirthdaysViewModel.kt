package com.rtv313isc.birthdayapp.presentation.list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rtv313isc.birthdayapp.data.di.MainDispatcher
import com.rtv313isc.birthdayapp.data.local.LocalBirthDay
import com.rtv313isc.birthdayapp.domain.GetBirthdaysUseCase
import com.rtv313isc.birthdayapp.domain.GetRepositoryUpdatedStateUseCase
import com.rtv313isc.birthdayapp.domain.RefreshBirthdaysUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BirthdaysViewModel @Inject constructor(
    private val refreshBirthdaysUseCase: RefreshBirthdaysUseCase,
    private val getBirthdaysUseCase: GetBirthdaysUseCase,
    private val getRepositoryUpdatedStateUseCase: GetRepositoryUpdatedStateUseCase,
    @MainDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _birthDaysState = mutableStateOf(mutableListOf<LocalBirthDay>())
    val birthDaysState: State<List<LocalBirthDay>> get() = _birthDaysState

    private val errorHandler = CoroutineExceptionHandler { _, exception ->
        exception.printStackTrace()
    }

    init {
        getBirthdays()
        refreshBirthdays()
    }

    private fun getBirthdays() {
        viewModelScope.launch(errorHandler + dispatcher) {
            getRepositoryUpdatedStateUseCase().collect {
                val birthDays = getBirthdaysUseCase()
                _birthDaysState.value = birthDays.toMutableList()
            }
        }
    }

    private fun refreshBirthdays() {
        viewModelScope.launch(errorHandler + dispatcher) {
            refreshBirthdaysUseCase()
        }
    }
}