package com.soft.mad_todo_list.todolistmodule.data

import com.soft.mad_todo_list.todolistmodule.data.model.TodoListItem
import javax.inject.Inject


class TodoListLocalDataSource @Inject constructor() : TodoListDataSource {
    private val items = mutableListOf(TodoListItem.mock1,TodoListItem.mock2,TodoListItem.mock3,TodoListItem.mock4)

    override  fun addItem(item: TodoListItem) {
        val itemWithId = item.copy(id = items.size+1)
        items.add(itemWithId)
    }

    override  fun editItem(id: Int, newItem: TodoListItem) {
        val index = items.indexOfFirst {
            it.id == id
        }
        items[index] = newItem
    }

    override  fun deleteItem(id: Int) {
        items.removeIf {
            it.id == id
        }
    }

    override fun fetchAll(): List<TodoListItem> = items
}