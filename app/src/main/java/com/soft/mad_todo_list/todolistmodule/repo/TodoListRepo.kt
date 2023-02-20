package com.soft.mad_todo_list.todolistmodule.repo

import com.soft.mad_todo_list.todolistmodule.data.TodoListDataSource
import com.soft.mad_todo_list.todolistmodule.data.TodoListLocalDataSource
import com.soft.mad_todo_list.todolistmodule.data.model.TodoListItem
import javax.inject.Inject

class TodoListRepo @Inject constructor(
   private val local: TodoListLocalDataSource
) {
    fun addItem(item: TodoListItem) {
        local.addItem(item)
    }

    fun editItem(id: Int, newItem: TodoListItem) {
        local.editItem(id, newItem)
    }

    fun deleteItem(id: Int) {
        local.deleteItem(id)
    }

    fun fetchAll(): List<TodoListItem>{
        return local.fetchAll()
    }

}