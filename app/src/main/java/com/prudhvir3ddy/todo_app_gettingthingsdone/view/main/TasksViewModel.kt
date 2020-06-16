package com.prudhvir3ddy.todo_app_gettingthingsdone.view.main

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.ExistingPeriodicWorkPolicy.KEEP
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.prudhvir3ddy.todo_app_gettingthingsdone.repository.ToDoRepository
import com.prudhvir3ddy.todo_app_gettingthingsdone.storage.SharedPrefs
import com.prudhvir3ddy.todo_app_gettingthingsdone.storage.db.ToDo
import com.prudhvir3ddy.todo_app_gettingthingsdone.workmanager.MyWorker
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit.MINUTES
import javax.inject.Inject

class TasksViewModel @Inject constructor(
  private val context: Context,
  private val repository: ToDoRepository,
  private val sharedPrefs: SharedPrefs
) : ViewModel() {

  val tasksList: LiveData<List<ToDo>> = getDataFromDb()

  fun setUpWorkManager() {
    val request = PeriodicWorkRequest.Builder(MyWorker::class.java, 15L, MINUTES)
      .build()
    val workManager = WorkManager.getInstance(context)
    workManager.enqueueUniquePeriodicWork("boo", KEEP, request)
  }

  fun getDataFromDb(): LiveData<List<ToDo>> {
    return repository.getAllToDos()
  }

  fun onTaskUpdate(todo: ToDo) {
    viewModelScope.launch {
      repository.updateToDo(todo)
    }
  }

  fun getFirstName(): String {
    return sharedPrefs.getFullName()
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