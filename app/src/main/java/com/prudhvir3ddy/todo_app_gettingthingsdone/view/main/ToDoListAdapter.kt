package com.prudhvir3ddy.todo_app_gettingthingsdone.view.main

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.prudhvir3ddy.todo_app_gettingthingsdone.databinding.ItemTodoBinding
import com.prudhvir3ddy.todo_app_gettingthingsdone.storage.db.ToDo
import com.prudhvir3ddy.todo_app_gettingthingsdone.view.main.ToDoListAdapter.ToDoListViewHolder
import kotlinx.android.synthetic.main.item_todo.view.todo_description
import kotlinx.android.synthetic.main.item_todo.view.todo_name
import org.jetbrains.annotations.NotNull

class ToDoListAdapter(
  private var items: List<ToDo>,
  private val itemClickListener: ItemClickListener
) :
  RecyclerView.Adapter<ToDoListViewHolder>(
  ) {

  fun submitList(items: List<ToDo>) {
    this.items = items
  }

  class ToDoListViewHolder(itemView: @NotNull ItemTodoBinding) :
    RecyclerView.ViewHolder(itemView.root) {

    val titleTextView = itemView.todoName
    val descriptionTextView = itemView.todoDescription
    val updateCheckBox = itemView.checkbox
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoListViewHolder {
    return ToDoListViewHolder(
      ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )
  }

  override fun onBindViewHolder(holder: ToDoListViewHolder, position: Int) {
    val item = items[position]
    holder.titleTextView.text = item.title
    holder.descriptionTextView.text = item.description
    holder.updateCheckBox.isChecked = item.isCompleted

    if (item.isCompleted) {
      holder.itemView.todo_name.strikeThrough()
      holder.itemView.todo_description.strikeThrough()
    }

    holder.itemView.setOnClickListener {
      itemClickListener.onClick(item)
    }
    holder.updateCheckBox.setOnCheckedChangeListener { _, isChecked ->
      item.isCompleted = isChecked
      if (isChecked) {
        holder.titleTextView.strikeThrough()
        holder.descriptionTextView.strikeThrough()
      } else {
        holder.titleTextView.removeStrikeThrough()
        holder.descriptionTextView.removeStrikeThrough()
      }
      itemClickListener.onUpdate(item)
    }
  }

  private fun TextView.strikeThrough() {
    this.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
  }

  private fun TextView.removeStrikeThrough() {
    this.paintFlags = this.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
  }

  override fun getItemCount(): Int = items.size

}