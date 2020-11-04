package com.wotin.practicemvvmpatterroomtodo.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.wotin.practicemvvmpatterroomtodo.model.TodoCustomClass

@Dao
interface TodoDao {

    // LiveData 는 활성 관찰자에게 업데이트 정보를 알린다.
    @Query("SELECT * from Todo")
    fun getTodoList() : LiveData<List<TodoCustomClass>>

    @Insert
    fun insertTodo(todo : TodoCustomClass)


    @Delete
    fun deleteTodo(todo : TodoCustomClass)
}