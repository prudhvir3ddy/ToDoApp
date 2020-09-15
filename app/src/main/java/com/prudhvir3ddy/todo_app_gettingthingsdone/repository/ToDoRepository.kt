package com.prudhvir3ddy.todo_app_gettingthingsdone.repository

import androidx.lifecycle.LiveData
import com.prudhvir3ddy.todo_app_gettingthingsdone.storage.db.ToDo
import com.prudhvir3ddy.todo_app_gettingthingsdone.storage.db.ToDoDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ToDoRepository @Inject constructor(
  private val toDoDatabase: ToDoDatabase
) {

  suspend fun addToDo(todo: ToDo) {
    withContext(Dispatchers.IO) {
      toDoDatabase.todoDao().insertToDo(todo)
    }
  }

  fun getAllToDos(): LiveData<List<ToDo>> {
    return toDoDatabase.todoDao().getAll()

  }

  suspend fun updateToDo(todo: ToDo) {
    withContext(Dispatchers.IO) {
      toDoDatabase.todoDao().updateToDo(todo)
    }
  }
}