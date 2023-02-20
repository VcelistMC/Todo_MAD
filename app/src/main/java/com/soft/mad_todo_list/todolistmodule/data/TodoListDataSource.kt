package com.soft.mad_todo_list.todolistmodule.data

import com.soft.mad_todo_list.todolistmodule.data.model.TodoListItem

interface TodoListDataSource {
    fun addItem(item: TodoListItem)
    fun editItem(id: Int, newItem: TodoListItem)
    fun deleteItem(id: Int)
    fun fetchAll(): List<TodoListItem>
}