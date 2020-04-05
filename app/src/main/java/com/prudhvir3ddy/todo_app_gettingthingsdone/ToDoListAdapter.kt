package com.prudhvir3ddy.todo_app_gettingthingsdone

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.prudhvir3ddy.todo_app_gettingthingsdone.ToDoListAdapter.ToDoListViewHolder
import kotlinx.android.synthetic.main.item_todo.view.todo_description
import kotlinx.android.synthetic.main.item_todo.view.todo_name

object ToDoDiffCallback : DiffUtil.ItemCallback<ToDo>() {
  override fun areItemsTheSame(oldItem: ToDo, newItem: ToDo): Boolean {
    return oldItem == newItem
  }

  override fun areContentsTheSame(oldItem: ToDo, newItem: ToDo): Boolean {
    return oldItem.name == newItem.name
  }

}

class ToDoListAdapter(private val itemClickListener: ItemClickListener) :
  ListAdapter<ToDo, ToDoListViewHolder>(
    ToDoDiffCallback
  ) {

  class ToDoListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoListViewHolder {
    return ToDoListViewHolder(
      LayoutInflater.from(parent.context).inflate(
        R.layout.item_todo,
        parent,
        false
      )
    )
  }

  override fun onBindViewHolder(holder: ToDoListViewHolder, position: Int) {
    holder.itemView.todo_name.text = getItem(position).name
    holder.itemView.todo_description.text = getItem(position).description

    holder.itemView.setOnClickListener {
      itemClickListener.onClick()
    }
  }
}