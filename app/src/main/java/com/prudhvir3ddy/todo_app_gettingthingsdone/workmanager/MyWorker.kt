package com.prudhvir3ddy.todo_app_gettingthingsdone.workmanager

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.prudhvir3ddy.todo_app_gettingthingsdone.ToDoApp
import com.prudhvir3ddy.todo_app_gettingthingsdone.storage.db.ToDoDatabase
import javax.inject.Inject

class MyWorker(
  val context: Context,
  workerParameters: WorkerParameters
) : Worker(context, workerParameters) {

  @Inject
  lateinit var toDoDatabase: ToDoDatabase

  override fun doWork(): Result {
    (applicationContext as ToDoApp).appComponent.inject(this)
    toDoDatabase.databaseWriteExecutor.execute {
      toDoDatabase.todoDao().deleteCompleted(true)
    }
    return Result.success()
  }
}
