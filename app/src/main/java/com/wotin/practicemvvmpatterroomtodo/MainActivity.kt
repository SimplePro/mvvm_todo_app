package com.wotin.practicemvvmpatterroomtodo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.wotin.practicemvvmpatterroomtodo.adapter.TodoRecyclerViewAdapter
import com.wotin.practicemvvmpatterroomtodo.databinding.ActivityMainBinding
import com.wotin.practicemvvmpatterroomtodo.model.TodoCustomClass
import com.wotin.practicemvvmpatterroomtodo.viewModel.TodoViewModel

class MainActivity : AppCompatActivity(), TodoRecyclerViewAdapter.TodoItemLongClickInterface {

    lateinit var mBinding : ActivityMainBinding
    lateinit var todoRecyclerViewAdapter : TodoRecyclerViewAdapter
    lateinit var todoViewModel : TodoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mBinding.activity = this@MainActivity

        todoViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(TodoViewModel::class.java)

        initTodoRecyclerView()

        // todoViewModel.getTodoList() 가 업데이트 정보를 받았다면(observe) -> 어댑터의 todoList 의 값을 바꿔주고, notifyDataSetChanged()
        todoViewModel.getTodoList().observe(this, Observer { todo ->
            todoRecyclerViewAdapter.todoList = todo
            todoRecyclerViewAdapter.notifyDataSetChanged()
        })

    }

    fun clickedAddButton() {
        todoViewModel.insertTodo(TodoCustomClass(todo = mBinding.todoEdittext.text.toString(), content = mBinding.contentEdittext.text.toString()))
    }

    fun initTodoRecyclerView() {
        todoRecyclerViewAdapter = TodoRecyclerViewAdapter(this)
        mBinding.todoRecyclerview.apply {
            adapter = todoRecyclerViewAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
        }
    }

    override fun todoItemLongClick(todo: TodoCustomClass) {
        todoViewModel.deleteTodo(todo)
    }

}