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
import java.util.concurrent.TimeUnit.MINUTES
import javax.inject.Inject

class TasksViewModel @Inject constructor(
  private val context: Context,
  private val todoDatabase: ToDoDatabase,
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
    return todoDatabase.todoDao().getAll()
  }

  fun onTaskUpdate(todo: ToDo) {
    todoDatabase.databaseWriteExecutor.execute {
      todoDatabase.todoDao().updateToDo(todo)
    }
  }

  fun getFullName(): String {
    return sharedPrefs.getFullName()
  }

  fun onTaskSave(taskName: String, taskDesc: String) {
    todoDatabase.databaseWriteExecutor.execute {
      todoDatabase.todoDao().insertToDo(
        ToDo(
          title = taskName,
          description = taskDesc
        )
      )
    }
  }
}