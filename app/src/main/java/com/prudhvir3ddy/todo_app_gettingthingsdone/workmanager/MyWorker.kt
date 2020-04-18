package com.prudhvir3ddy.todo_app_gettingthingsdone.workmanager

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.prudhvir3ddy.todo_app_gettingthingsdone.storage.db.ToDoDatabase

class MyWorker(
  val context: Context,
  workerParameters: WorkerParameters,
  private val toDoDatabase: ToDoDatabase
) : Worker(context, workerParameters) {

  override fun doWork(): Result {
    toDoDatabase.databaseWriteExecutor.execute {
      toDoDatabase.todoDao().deleteCompleted(true)
    }
    return Result.success()
  }
}
