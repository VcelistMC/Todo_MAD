package com.soft.mad_todo_list.common

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.withStarted
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.reflect.ParameterizedType

open class BaseFragment<VM: BaseViewModel<STATE>, STATE: UiState>: Fragment(), OnFragmentCreated {
    lateinit var viewModel: VM

    @Suppress("UNCHECKED_CAST")
    private val viewModelType: Class<VM>
        get() {
            return (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<VM>
        }

    private fun createViewModel(): VM {
        val t = viewModelType
        Log.v("vmt", t.name)
        return ViewModelProvider(this).get(viewModelType)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = createViewModel()
        viewModel.initState()
        initListeners()
        initObservers()
        initRecycler()
        observeState()
    }

    protected open fun observeState() {
        viewModel.uiState().observe(this){
            handleState(it)
        }
    }

    open fun handleState(newState: STATE){}



    @Suppress("UNCHECKED_CAST")
    val baseActivity: BaseActivity<VM, STATE>
    get(){
        return activity as BaseActivity<VM, STATE>
    }

    val container: Int
    get() {
        return baseActivity.container
    }


    fun addFragment(fragment: Fragment){
        val backStackName: String = fragment::javaClass.toString()
        activity?.supportFragmentManager?.beginTransaction()
            ?.add(container, fragment, tag)?.addToBackStack(backStackName)
            ?.commit()
    }

    override fun initObservers() {}
    override fun initRecycler() {}
    override fun initListeners() {}
}