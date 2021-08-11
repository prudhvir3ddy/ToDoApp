package com.prudhvir3ddy.todo_app_gettingthingsdone.view.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.prudhvir3ddy.todo_app_gettingthingsdone.repository.ToDoRepository
import com.prudhvir3ddy.todo_app_gettingthingsdone.storage.SharedPrefs
import com.prudhvir3ddy.todo_app_gettingthingsdone.storage.db.ToDo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
  private val repository: ToDoRepository,
  private val sharedPrefs: SharedPrefs
) : ViewModel() {

  val tasksList: LiveData<List<ToDo>> = repository.getAllToDos().asLiveData()

  fun onTaskToggle(todo: ToDo, isCompleted: Boolean) {
    viewModelScope.launch {
      val updatedToDo = todo.copy(isCompleted = isCompleted)
      repository.updateToDo(updatedToDo)
    }
  }

  fun updateTaskList(todoList: List<ToDo>) {
    viewModelScope.launch {
      repository.addToDos(todoList)
    }
  }

  fun onTaskUpdate(todo: ToDo) {
    viewModelScope.launch {
      repository.updateToDo(todo)
    }
  }

  fun getFirstName(): String {
    return sharedPrefs.getFullName()
  }

  fun onTaskDelete(taskId: String?) {
    viewModelScope.launch {
      repository.deleteToDo(taskId)
    }
  }

  fun getTaskByIdAsync(taskId: String?): ToDo {
    return runBlocking {
      repository.getTaskById(taskId)
    }
  }

  fun onTaskSave(taskName: String, taskDesc: String) {
    viewModelScope.launch {
      val todo = ToDo(
        title = taskName,
        description = taskDesc
      )
      repository.addToDo(todo)
    }
  }
}