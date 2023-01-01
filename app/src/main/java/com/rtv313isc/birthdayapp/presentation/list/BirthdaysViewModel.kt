package com.rtv313isc.birthdayapp.presentation.list

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rtv313isc.birthdayapp.data.di.MainDispatcher
import com.rtv313isc.birthdayapp.domain.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BirthdaysViewModel @Inject constructor(
    private val app: Application,
    private val refreshBirthdaysUseCase: RefreshBirthdaysUseCase,
    private val getBirthdaysUseCase: GetBirthdaysUseCase,
    private val getRepositoryUpdatedStateUseCase: GetRepositoryUpdatedStateUseCase,
    private val logoutUseCase: LogoutUseCase,
    @MainDispatcher private val dispatcher: CoroutineDispatcher
) : AndroidViewModel(app) {

    private val _birthDaysState = mutableStateOf(mutableListOf<Birthday>())
    val birthDaysState: State<List<Birthday>> get() = _birthDaysState

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

    fun logout() {
        viewModelScope.launch(errorHandler + dispatcher) {
            logoutUseCase(app.applicationContext)
        }
    }
}