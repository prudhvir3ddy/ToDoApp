package com.prudhvir3ddy.todo_app_gettingthingsdone.storage.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo")
data class ToDoTask(
  @PrimaryKey(autoGenerate = true)
  val id: Int,
  val title: String,
  val description: String,
  val imagePath: String,
  val isCompleted: Boolean = false
)