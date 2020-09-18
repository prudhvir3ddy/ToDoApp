package com.prudhvir3ddy.todo_app_gettingthingsdone.storage.db

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.UUID

@Parcelize
@Entity(tableName = "todo")
data class ToDo(
  @PrimaryKey
  val id: String = UUID.randomUUID().toString(),
  var title: String = "",
  var description: String = "",
  var isCompleted: Boolean = false
) : Parcelable