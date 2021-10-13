package com.plcoding.jetpackcomposepokedex.presentation.base

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.jetpackcomposepokedex.utils.base.SingleEventWithContent
import kotlinx.coroutines.launch
import timber.log.Timber

abstract class BaseViewModel : ViewModel() {

    val errorEvent = SingleEventWithContent<Exception>()

    fun runAsync(isLoading: MutableState<Boolean>? = null, tryFunction: suspend () -> Unit) {
        viewModelScope.launch {
            try {
                isLoading?.value = true
                tryFunction()
            } catch (exception: Exception) {
                errorEvent.send(exception)
                Timber.e(exception)
            } finally {
                isLoading?.value = false
            }
        }
    }

}