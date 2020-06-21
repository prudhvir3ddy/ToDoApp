package com.prudhvir3ddy.todo_app_gettingthingsdone.view.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prudhvir3ddy.todo_app_gettingthingsdone.storage.db.ToDo
import com.prudhvir3ddy.todo_app_gettingthingsdone.storage.db.ToDoDatabase
import kotlinx.coroutines.launch

class DetailViewModel @ViewModelInject constructor(
  private val toDoDatabase: ToDoDatabase
) : ViewModel() {

  fun updateToDo(todo: ToDo) {
    viewModelScope.launch {
      toDoDatabase.todoDao().updateToDo(todo)
    }
  }
}