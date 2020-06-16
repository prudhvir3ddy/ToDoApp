package com.prudhvir3ddy.todo_app_gettingthingsdone.repository

import androidx.lifecycle.LiveData
import com.prudhvir3ddy.todo_app_gettingthingsdone.storage.db.ToDo
import com.prudhvir3ddy.todo_app_gettingthingsdone.storage.db.ToDoDatabase
import javax.inject.Inject

class ToDoRepository @Inject constructor(
  private val toDoDatabase: ToDoDatabase
) {

  suspend fun addToDo(todo: ToDo) {
    toDoDatabase.todoDao().insertToDo(todo)
  }

  fun getAllToDos(): LiveData<List<ToDo>> {
    return toDoDatabase.todoDao().getAll()
  }

  suspend fun updateToDo(todo: ToDo) {
    toDoDatabase.todoDao().updateToDo(todo)
  }
}