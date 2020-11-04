package com.wotin.practicemvvmpatterroomtodo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.wotin.practicemvvmpatterroomtodo.dao.TodoDao
import com.wotin.practicemvvmpatterroomtodo.model.TodoCustomClass

@Database(entities = [TodoCustomClass::class], version = 1)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun todoDao() : TodoDao
}