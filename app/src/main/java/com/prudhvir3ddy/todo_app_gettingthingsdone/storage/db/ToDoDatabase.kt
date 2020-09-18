package com.prudhvir3ddy.todo_app_gettingthingsdone.storage.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ToDo::class], version = 1, exportSchema = false)
abstract class ToDoDatabase : RoomDatabase() {

  abstract fun todoDao(): ToDoDao

  companion object {
    fun getInstance(context: Context): ToDoDatabase {
      return Room.databaseBuilder(context, ToDoDatabase::class.java, "todo.db").build()
    }
  }
}