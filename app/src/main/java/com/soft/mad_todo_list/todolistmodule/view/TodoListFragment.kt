package com.soft.mad_todo_list.todolistmodule.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.soft.mad_todo_list.common.AlertDialogHelper
import com.soft.mad_todo_list.common.BaseFragment
import com.soft.mad_todo_list.databinding.FragmentTodoListBinding
import com.soft.mad_todo_list.todolistmodule.adapter.ItemActions
import com.soft.mad_todo_list.todolistmodule.adapter.MainListAdapter
import com.soft.mad_todo_list.todolistmodule.data.model.TodoListItem
import com.soft.mad_todo_list.todolistmodule.state.TodoListUiState
import com.soft.mad_todo_list.todolistmodule.viewmodel.TodoListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TodoListFragment : BaseFragment<TodoListViewModel, TodoListUiState>(), ItemActions {
    private lateinit var binding: FragmentTodoListBinding
    private lateinit var adapter: MainListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTodoListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchAll()
    }

    override fun initRecycler() {
        adapter = MainListAdapter(emptyList(), this)
        binding.listRv.layoutManager = LinearLayoutManager(requireContext())
        binding.listRv.adapter = adapter
    }

    override fun initListeners() {
        listenToAddBtn()
    }

    private fun listenToAddBtn() {
        binding.addTaskBtn.setOnClickListener {
            AlertDialogHelper.showInputDialog(requireContext(), "Add Task"){
                val newTask = TodoListItem(description = it)
                viewModel.addItem(newTask)
            }
        }
    }

    override fun handleState(newState: TodoListUiState) {
        toggleLoading(newState.isLoading)
        if(newState.items.isNotEmpty()){
            toggleListVisibility(true)
            adapter.updateList(newState.items)
        }
    }

    private fun toggleLoading(shown: Boolean){
        binding.progressCircular.visibility = if(shown) View.VISIBLE else View.GONE
    }

    private fun toggleListVisibility(shown:Boolean){
        binding.listRv.visibility = if(shown) View.VISIBLE else View.GONE
    }



    companion object {
        fun newInstance() = TodoListFragment()
    }

    override fun onItemDeleted(deletedItem: TodoListItem) {
        viewModel.deleteItem(deletedItem.id)
    }

    override fun onItemEdited(itemToEdit: TodoListItem) {
        AlertDialogHelper.showInputDialog(
            requireContext(),
            "Edit Task"
        ){
            viewModel.editItem(itemToEdit.id, itemToEdit.copy(description = it))
        }
    }

    override fun onItemToggled(toggledItem: TodoListItem, isChecked: Boolean) {
        viewModel.editItem(
            toggledItem.id,
            toggledItem.copy(isCompleted = isChecked)
        )
    }
}