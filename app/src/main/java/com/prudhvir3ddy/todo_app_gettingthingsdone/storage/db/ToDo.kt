package com.prudhvir3ddy.todo_app_gettingthingsdone.storage.db

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "todo")
data class ToDo(
  @PrimaryKey(autoGenerate = true)
  val id: Int? = null,
  val title: String = "",
  val description: String = "",
  val imagePath: String = "",
  var isCompleted: Boolean = false
) : Parcelable