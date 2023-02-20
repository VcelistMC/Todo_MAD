package com.soft.mad_todo_list.todolistmodule.state

import com.soft.mad_todo_list.common.UiState
import com.soft.mad_todo_list.todolistmodule.data.model.TodoListItem

data class TodoListUiState(
    val isLoading: Boolean = false,
    val items: List<TodoListItem> = listOf(),
    val errorMsg: String? = null
): UiState