package com.soft.mad_todo_list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.soft.mad_todo_list.common.BaseActivity
import com.soft.mad_todo_list.common.BaseViewModel
import com.soft.mad_todo_list.common.UiState
import com.soft.mad_todo_list.todolistmodule.view.TodoListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<BaseViewModel<UiState>, UiState>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setContainerView(R.id.mainLayout)
        addFragment(TodoListFragment.newInstance())
    }
}