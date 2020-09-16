package com.prudhvir3ddy.todo_app_gettingthingsdone.storage.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface ToDoDao {

  @Query("SELECT * FROM todo")
  fun getAll(): LiveData<List<ToDo>>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertToDo(toDo: ToDo)

  @Update
  suspend fun updateToDo(toDo: ToDo)

  @Query("DELETE FROM todo WHERE id =:taskId")
  suspend fun delete(taskId: String?)

  @Query("DELETE FROM todo WHERE isCompleted =:status")
  suspend fun deleteCompleted(status: Boolean)
}