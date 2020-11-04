package com.wotin.practicemvvmpatterroomtodo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wotin.practicemvvmpatterroomtodo.databinding.TodoRecyclerviewItemBinding
import com.wotin.practicemvvmpatterroomtodo.model.TodoCustomClass

class TodoRecyclerViewAdapter(val todoItemLongClickInterface: TodoItemLongClickInterface) : RecyclerView.Adapter<TodoRecyclerViewAdapter.CustomViewHolder>() {

    var todoList : List<TodoCustomClass> = arrayListOf()

    interface TodoItemLongClickInterface {
        fun todoItemLongClick(todo : TodoCustomClass)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val binding = TodoRecyclerviewItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return CustomViewHolder(binding).apply {
            itemView.setOnLongClickListener {
                todoItemLongClickInterface.todoItemLongClick(todoList[adapterPosition])
                return@setOnLongClickListener true
            }
        }
    }

    override fun getItemCount(): Int = todoList.size

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.onBind(todoList[position])
    }

    class CustomViewHolder(val binding : TodoRecyclerviewItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data : TodoCustomClass) {
            binding.todo = data
        }

    }
}
