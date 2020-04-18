package com.prudhvir3ddy.todo_app_gettingthingsdone.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.work.ExistingPeriodicWorkPolicy.KEEP
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.prudhvir3ddy.todo_app_gettingthingsdone.storage.SharedPrefs
import com.prudhvir3ddy.todo_app_gettingthingsdone.storage.db.ToDo
import com.prudhvir3ddy.todo_app_gettingthingsdone.storage.db.ToDoDatabase
import com.prudhvir3ddy.todo_app_gettingthingsdone.workmanager.MyWorker
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.util.concurrent.TimeUnit.MINUTES

class TasksViewModel(
  private val context: Context
) : ViewModel(), KoinComponent {

  private val toDoDatabase: ToDoDatabase by inject()
  private val sharedPrefs: SharedPrefs by inject()

  val tasksList: LiveData<List<ToDo>> = getDataFromDb()

  fun setUpWorkManager() {
    val request = PeriodicWorkRequest.Builder(MyWorker::class.java, 15L, MINUTES)
      .build()
    WorkManager.getInstance(context).enqueueUniquePeriodicWork("boo", KEEP, request)
  }

  fun getDataFromDb(): LiveData<List<ToDo>> {
    return toDoDatabase.todoDao().getAll()
  }

  fun onTaskUpdate(todo: ToDo) {
    toDoDatabase.databaseWriteExecutor.execute {
      toDoDatabase.todoDao().updateToDo(todo)
    }
  }

  fun getFullName(): String {
    return sharedPrefs.getFullName()
  }

  fun onTaskSave(taskName: String, taskDesc: String) {
    toDoDatabase.databaseWriteExecutor.execute {
      toDoDatabase.todoDao().insertToDo(
        ToDo(
          title = taskName,
          description = taskDesc
        )
      )
    }
  }
}