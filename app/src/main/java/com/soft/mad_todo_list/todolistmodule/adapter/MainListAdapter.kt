package com.soft.mad_todo_list.todolistmodule.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.soft.mad_todo_list.databinding.TodoListItemBinding
import com.soft.mad_todo_list.todolistmodule.data.model.TodoListItem

class MainListAdapter(
    private var todos: List<TodoListItem>,
    private val listener: ItemActions?
): RecyclerView.Adapter<MainListAdapter.TodoItemViewHolder>(){

    class TodoItemViewHolder(val itemBinding: TodoListItemBinding, val listener: ItemActions?) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bindItemToView(item: TodoListItem){
            itemBinding.isCompletedCB.isChecked = item.isCompleted
            itemBinding.todoText.text = item.description
        }

        fun listenToCheckBox(item: TodoListItem){
            itemBinding.isCompletedCB.setOnCheckedChangeListener { _, isChecked ->
                listener?.onItemToggled(item, isChecked)
            }
        }

        fun listenToDeletion(item: TodoListItem){
            itemBinding.delete.setOnClickListener {
                listener?.onItemDeleted(item)
            }
        }

        fun listenToEdit(currentItem: TodoListItem) {
            itemBinding.editBtn.setOnClickListener {
                listener?.onItemEdited(currentItem)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoItemViewHolder {
        val binding = TodoListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodoItemViewHolder(binding, listener)
    }

    override fun getItemCount() = todos.size

    fun updateList(list: List<TodoListItem>){
        todos = list
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: TodoItemViewHolder, position: Int) {
        val currentItem = todos[position]

        holder.bindItemToView(currentItem)
        holder.listenToCheckBox(currentItem)
        holder.listenToDeletion(currentItem)
        holder.listenToEdit(currentItem)
    }
}

interface ItemActions{
    fun onItemDeleted(deletedItem: TodoListItem)
    fun onItemEdited(itemToEdit: TodoListItem)
    fun onItemToggled(toggledItem: TodoListItem, isChecked: Boolean)
}