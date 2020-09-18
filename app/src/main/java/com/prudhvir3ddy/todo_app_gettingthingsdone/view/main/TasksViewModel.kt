package com.prudhvir3ddy.todo_app_gettingthingsdone.view.main

import android.content.Context
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.ExistingPeriodicWorkPolicy.KEEP
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.prudhvir3ddy.todo_app_gettingthingsdone.repository.ToDoRepository
import com.prudhvir3ddy.todo_app_gettingthingsdone.storage.SharedPrefs
import com.prudhvir3ddy.todo_app_gettingthingsdone.storage.db.ToDo
import com.prudhvir3ddy.todo_app_gettingthingsdone.utils.Event
import com.prudhvir3ddy.todo_app_gettingthingsdone.workmanager.MyWorker
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit.MINUTES

class TasksViewModel @ViewModelInject constructor(
  @ApplicationContext private val context: Context,
  private val repository: ToDoRepository,
  private val sharedPrefs: SharedPrefs
) : ViewModel() {

  val tasksList: LiveData<List<ToDo>> = repository.getAllToDos()

  private val _editTaskEvent = MutableLiveData<Event<ToDo>>()
  val editTaskEvent: LiveData<Event<ToDo>> = _editTaskEvent

  fun setUpWorkManager() {
    val request = PeriodicWorkRequest.Builder(MyWorker::class.java, 15L, MINUTES)
      .build()
    val workManager = WorkManager.getInstance(context)
    workManager.enqueueUniquePeriodicWork("boo", KEEP, request)
  }

  fun onTaskToggle(todo: ToDo, isCompleted: Boolean) {
    viewModelScope.launch {
      val updatedToDo = todo.copy(isCompleted = isCompleted)
      repository.updateToDo(updatedToDo)
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

  fun editToDo(todo: ToDo) {
    _editTaskEvent.value = Event(todo)
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