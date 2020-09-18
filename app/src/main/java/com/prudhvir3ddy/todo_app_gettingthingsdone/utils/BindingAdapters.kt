package com.prudhvir3ddy.todo_app_gettingthingsdone.utils

import android.graphics.Paint
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.prudhvir3ddy.todo_app_gettingthingsdone.storage.db.ToDo
import com.prudhvir3ddy.todo_app_gettingthingsdone.view.main.ToDoListAdapter

@BindingAdapter("strikeThrough")
fun strikeThrough(textView: TextView, isCompleted: Boolean) {
  if (isCompleted) {
    textView.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
  } else {
    textView.paintFlags = textView.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
  }
}

@BindingAdapter("app:items")
fun setItems(listView: RecyclerView, items: List<ToDo>?) {
  items?.let {
    (listView.adapter as ToDoListAdapter).submitList(items)
  }
}