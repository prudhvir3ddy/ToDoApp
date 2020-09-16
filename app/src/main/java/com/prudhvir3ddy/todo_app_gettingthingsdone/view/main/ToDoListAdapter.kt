package com.prudhvir3ddy.todo_app_gettingthingsdone.view.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.prudhvir3ddy.todo_app_gettingthingsdone.databinding.ItemTodoBinding
import com.prudhvir3ddy.todo_app_gettingthingsdone.storage.db.ToDo
import com.prudhvir3ddy.todo_app_gettingthingsdone.view.main.ToDoListAdapter.ToDoListViewHolder

class ToDoListAdapter(
  private val viewModel: TasksViewModel
) :
  ListAdapter<ToDo, ToDoListViewHolder>(
    TaskDiffCallback()
  ) {

  class ToDoListViewHolder private constructor(val binding: ItemTodoBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
      todo: ToDo,
      viewModel: TasksViewModel
    ) {
      binding.todo = todo
      binding.viewModel = viewModel
      binding.executePendingBindings()
    }

    companion object {
      fun from(parent: ViewGroup): ToDoListViewHolder {
        return ToDoListViewHolder(
          ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
      }
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoListViewHolder {
    return ToDoListViewHolder.from(parent)
  }

  override fun onBindViewHolder(holder: ToDoListViewHolder, position: Int) {
    val item = getItem(position)
    holder.bind(item, viewModel)
  }

}

class TaskDiffCallback : DiffUtil.ItemCallback<ToDo>() {
  override fun areItemsTheSame(oldItem: ToDo, newItem: ToDo): Boolean {
    return oldItem.id == newItem.id
  }

  override fun areContentsTheSame(oldItem: ToDo, newItem: ToDo): Boolean {
    return oldItem == newItem
  }
}