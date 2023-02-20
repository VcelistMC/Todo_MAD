package com.soft.mad_todo_list.todolistmodule.data.model

data class TodoListItem (
    val id: Int = -1,
    val description: String,
    val isCompleted: Boolean = false,
){
    companion object{
        val mock1 = TodoListItem(
            1,
            "Buy Bread",
            false,
        )

        val mock2 = TodoListItem(
            2,
            "Buy Milk",
            true,
        )

        val mock3 = TodoListItem(
            3,
            "Buy Tomatoes",
            false,
        )

        val mock4 = TodoListItem(
            4,
            "Buy Pizza",
            false,
        )

        val mockList = listOf(mock1, mock2, mock3, mock4)
    }
}