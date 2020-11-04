package com.wotin.practicemvvmpatterroomtodo.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.wotin.practicemvvmpatterroomtodo.model.TodoCustomClass
import com.wotin.practicemvvmpatterroomtodo.repositories.TodoRepository

class TodoViewModel(application: Application) : AndroidViewModel(application) {
    private val mTodoRepository : TodoRepository = TodoRepository(application = application)
    private var mTodoItems : LiveData<List<TodoCustomClass>>

    init {
        mTodoItems = mTodoRepository.getTodoList()  // mTodoItems 는 repository 의 mTodoItems 를 바라보고 있고, repository 의 mTodoItems 는 DB 를 바라보고 있다.
        // DB 값이 변경되면 -> repository.mTodoItems 값 변경 (자료형이 LiveData 인 값을 바라보고 있기 때문.) -> viewModel.mTodoItems 값 변경 (자료형이 LiveData 인 값을 바라보고 있기 때문.)
        // 자료형이 LiveData 면 활성 관찰자에게 업데이트 내용을 알려 갱신되도록 함.
    }

    fun getTodoList() : LiveData<List<TodoCustomClass>> {
        return mTodoItems
    }

    fun insertTodo(todo : TodoCustomClass) {
        mTodoRepository.insertTodo(todo)
    }


    fun deleteTodo(todo : TodoCustomClass) {
        mTodoRepository.deleteTodo(todo)
    }
}