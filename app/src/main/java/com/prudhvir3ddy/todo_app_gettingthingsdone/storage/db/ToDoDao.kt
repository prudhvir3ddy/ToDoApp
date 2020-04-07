package com.prudhvir3ddy.todo_app_gettingthingsdone.storage.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface ToDoDao {

  @Query("SELECT * FROM todo")
  fun getAll(): List<ToDoTask>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertToDo(toDoTask: ToDoTask)

  @Update
  fun updateToDo(toDoTask: ToDoTask)

  @Delete
  fun delete(toDoTask: ToDoTask)
}