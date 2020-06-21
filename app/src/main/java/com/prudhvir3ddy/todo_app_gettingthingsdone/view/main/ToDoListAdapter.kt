package com.prudhvir3ddy.todo_app_gettingthingsdone.view.main

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.prudhvir3ddy.todo_app_gettingthingsdone.R.layout
import com.prudhvir3ddy.todo_app_gettingthingsdone.storage.db.ToDo
import com.prudhvir3ddy.todo_app_gettingthingsdone.view.main.ToDoListAdapter.ToDoListViewHolder
import kotlinx.android.synthetic.main.item_todo.view.checkbox
import kotlinx.android.synthetic.main.item_todo.view.todo_description
import kotlinx.android.synthetic.main.item_todo.view.todo_name

object ToDoDiffCallback : DiffUtil.ItemCallback<ToDo>() {
  override fun areItemsTheSame(oldItem: ToDo, newItem: ToDo): Boolean {
    return oldItem == newItem
  }

  override fun areContentsTheSame(oldItem: ToDo, newItem: ToDo): Boolean {
    return oldItem.title == newItem.title
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
        layout.item_todo,
        parent,
        false
      )
    )
  }

  override fun onBindViewHolder(holder: ToDoListViewHolder, position: Int) {
    holder.itemView.todo_name.text = getItem(position).title
    holder.itemView.todo_description.text = getItem(position).description
    holder.itemView.checkbox.isChecked = getItem(position).isCompleted

    if (getItem(position).isCompleted) {
      holder.itemView.todo_name.strikeThrough()
      holder.itemView.todo_description.strikeThrough()
    }
    holder.itemView.setOnClickListener {
      itemClickListener.onClick(getItem(position))
    }
    holder.itemView.checkbox.setOnCheckedChangeListener { _, isChecked ->
      getItem(position).isCompleted = isChecked
      if (isChecked) {
        holder.itemView.todo_name.strikeThrough()
        holder.itemView.todo_description.strikeThrough()
      } else {
        holder.itemView.todo_name.removeStrikeThrough()
        holder.itemView.todo_description.removeStrikeThrough()
      }
      itemClickListener.onUpdate(getItem(position))
    }
  }

  private fun TextView.strikeThrough() {
    this.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
  }

  private fun TextView.removeStrikeThrough() {
    this.paintFlags = this.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
  }
}