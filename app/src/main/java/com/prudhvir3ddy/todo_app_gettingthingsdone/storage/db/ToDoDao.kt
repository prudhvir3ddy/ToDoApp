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
  fun getAll(): List<ToDo>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertToDo(toDo: ToDo)

  @Update
  fun updateToDo(toDo: ToDo)

  @Delete
  fun delete(toDo: ToDo)
}