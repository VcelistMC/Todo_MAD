package com.soft.mad_todo_list.common

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


abstract class BaseViewModel<STATE: UiState>(application: Application) : AndroidViewModel(application) {

    // utility function to make it easier to update the state while utilizing the previous state if needed
    // this function is the same as StateFlow.update(), but unlike StateFlow.update()
    // this function doesn't compare the old state with the new state before notifying the observers
    //
    protected fun MutableLiveData<STATE>.update(update: (STATE) -> STATE){
        postValue(
            update(this.value!!)
        )
    }

    protected lateinit var _uiState: MutableLiveData<STATE>
    fun uiState(): LiveData<STATE> = _uiState

    // function to force any clients for this Base class to specify the initial state of the UI
    abstract fun initState()
}

