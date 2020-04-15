package com.prudhvir3ddy.todo_app_gettingthingsdone.storage.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@Database(entities = [ToDo::class], version = 1, exportSchema = false)
abstract class ToDoDatabase : RoomDatabase() {

  abstract fun todoDao(): ToDoDao
  private val NUMBER_OF_THREADS = 4
  val databaseWriteExecutor: ExecutorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS)

  companion object {
    fun getInstance(context: Context): ToDoDatabase {
      return Room.databaseBuilder(context, ToDoDatabase::class.java, "todo.db").build()
    }
  }
}