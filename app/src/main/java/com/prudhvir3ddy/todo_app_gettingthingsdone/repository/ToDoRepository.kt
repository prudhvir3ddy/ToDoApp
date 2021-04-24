package com.prudhvir3ddy.todo_app_gettingthingsdone.repository

import com.prudhvir3ddy.todo_app_gettingthingsdone.storage.db.ToDo
import com.prudhvir3ddy.todo_app_gettingthingsdone.storage.db.ToDoDatabase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Not running most database queries on i/o thread
 * because room will shift itself
 */
class ToDoRepository @Inject constructor(
  private val toDoDatabase: ToDoDatabase
) {

  suspend fun addToDo(todo: ToDo) = toDoDatabase.todoDao().insertToDo(todo)

  fun getAllToDos(): Flow<List<ToDo>> = toDoDatabase.todoDao().getAllToDos()

  suspend fun updateToDo(todo: ToDo) = toDoDatabase.todoDao().updateToDo(todo)

  suspend fun deleteToDo(taskId: String?) = toDoDatabase.todoDao().delete(taskId)

  suspend fun addToDos(todoList: List<ToDo>) = toDoDatabase.todoDao().addToDos(todoList)

}