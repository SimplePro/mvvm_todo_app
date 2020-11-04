package com.wotin.practicemvvmpatterroomtodo.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Todo")
data class TodoCustomClass (
    @PrimaryKey(autoGenerate = true)
    val id : Long? = null,

    @ColumnInfo(name = "todo")
    val todo : String,

    @ColumnInfo(name = "content")
    val content : String
) {
    override fun toString(): String {
        return "id: $id, todo: $todo, content: $content"
    }
}