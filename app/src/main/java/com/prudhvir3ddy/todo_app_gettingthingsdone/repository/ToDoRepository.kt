package com.prudhvir3ddy.todo_app_gettingthingsdone.repository

import androidx.lifecycle.LiveData
import com.prudhvir3ddy.todo_app_gettingthingsdone.storage.db.ToDo
import com.prudhvir3ddy.todo_app_gettingthingsdone.storage.db.ToDoDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
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

  fun getAllToDos(): Flow<List<ToDo>> {
    return toDoDatabase.todoDao().getAllToDos()

  }

  suspend fun updateToDo(todo: ToDo) {
    withContext(Dispatchers.IO) {
      toDoDatabase.todoDao().updateToDo(todo)
    }
  }

  suspend fun deleteToDo(taskId: String?) {
    withContext(Dispatchers.IO) {
      toDoDatabase.todoDao().delete(taskId)
    }
  }

  suspend fun addToDos(todoList: List<ToDo>) {
    withContext(Dispatchers.IO) {
      toDoDatabase.todoDao().addToDos(todoList)
    }
  }
}