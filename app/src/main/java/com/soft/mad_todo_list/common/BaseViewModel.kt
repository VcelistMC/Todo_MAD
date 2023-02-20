package com.soft.mad_todo_list.common

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


abstract class BaseViewModel<STATE: UiState>(application: Application) : AndroidViewModel(application) {

    protected fun MutableLiveData<STATE>.update(update: (STATE) -> STATE){
        postValue(
            update(this.value!!)
        )
    }

    protected lateinit var _uiState: MutableLiveData<STATE>
    fun uiState(): LiveData<STATE> = _uiState


    abstract fun initState()
}

