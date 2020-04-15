package com.prudhvir3ddy.todo_app_gettingthingsdone.storage.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo")
data class ToDo(
  @PrimaryKey(autoGenerate = true)
  val id: Int? = null,
  val title: String = "",
  val description: String = "",
  val imagePath: String = "",
  var isCompleted: Boolean = false
)