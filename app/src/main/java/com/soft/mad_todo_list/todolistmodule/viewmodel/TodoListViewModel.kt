package com.soft.mad_todo_list.todolistmodule.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.soft.mad_todo_list.common.BaseViewModel
import com.soft.mad_todo_list.todolistmodule.data.TodoListLocalDataSource
import com.soft.mad_todo_list.todolistmodule.data.model.TodoListItem
import com.soft.mad_todo_list.todolistmodule.repo.TodoListRepo
import com.soft.mad_todo_list.todolistmodule.state.TodoListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(
    application: Application,
    private val repo: TodoListRepo
) : BaseViewModel<TodoListUiState>(application) {

    fun fetchAll(){
        val all = repo.fetchAll()
        _uiState.update {
            it.copy(isLoading = false, items = all)
        }
    }

    fun addItem(item: TodoListItem){
        repo.addItem(item)
        _uiState.update {
            it.copy(
                items = repo.fetchAll()
            )
        }
    }

    fun editItem(id: Int, newItem: TodoListItem){
        repo.editItem(id, newItem)
        _uiState.update {
            it.copy(
                items = repo.fetchAll()
            )
        }
    }

    fun deleteItem(id: Int){
        repo.deleteItem(id)
        _uiState.update {
            it.copy(
                items = repo.fetchAll()
            )
        }
    }

    override fun initState() {
        _uiState = MutableLiveData(TodoListUiState())
    }

}