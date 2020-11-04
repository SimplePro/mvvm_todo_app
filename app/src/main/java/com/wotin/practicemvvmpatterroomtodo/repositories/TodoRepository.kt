package com.wotin.practicemvvmpatterroomtodo.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.wotin.practicemvvmpatterroomtodo.dao.TodoDao
import com.wotin.practicemvvmpatterroomtodo.database.TodoDatabase
import com.wotin.practicemvvmpatterroomtodo.model.TodoCustomClass

class TodoRepository(application: Application) {
    private var mTodoDatabase : TodoDatabase = Room.databaseBuilder(
        application.applicationContext,
        TodoDatabase::class.java, "Todo.db"
    )
        .build()

    private var mTodoDao : TodoDao
    private var mTodoItems : LiveData<List<TodoCustomClass>>

    init {
        mTodoDao = mTodoDatabase.todoDao()
        mTodoItems = mTodoDao.getTodoList()  // mTodoItems 는 getTodoList 를 바라보고 있고, getTodoList 에게 업데이트 정보를 받을 때마다 값이 갱신된다.
    }

    fun getTodoList() : LiveData<List<TodoCustomClass>> {
        return mTodoItems
    }

    fun insertTodo(todo : TodoCustomClass) {
        Thread(Runnable {
            mTodoDao.insertTodo(todo)
        }).start()
    }

    fun deleteTodo(todo : TodoCustomClass) {
        Thread(Runnable {
            mTodoDao.deleteTodo(todo)
        }).start()
    }

}